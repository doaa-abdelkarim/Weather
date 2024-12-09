package com.example.weather.presentation.features.home.screens

import android.Manifest
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.extensions.findActivity
import com.example.core.extensions.isLargeScreen
import com.example.core.utils.LocationUtil
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.presentation.common.RequestPermission
import com.example.weather.presentation.common.UIState
import com.example.weather.presentation.constants.enum.MeasurementUnit
import com.example.weather.presentation.features.home.intents.ListForecastIntent
import com.example.weather.presentation.features.home.viewmodels.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToSearchCityScreen: () -> Unit,
) {
    val context = LocalContext.current
    var isLocationGranted by rememberSaveable { mutableStateOf(false) }
    Scaffold { innerPadding ->
        if (!isLocationGranted)
            RequestPermission(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                initialContent = { onRequestPermission ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.we_kindly_request_your_permission_to_access_your_current_location),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = onRequestPermission) {
                            Text(text = stringResource(R.string.give_permission))
                        }
                    }
                },
                grantedContent = {
                    HomeLayout(
                        context = context,
                        innerPadding = innerPadding,
                        navigateToSearchCityScreen = navigateToSearchCityScreen,
                        weatherState = homeViewModel.currentWeather.collectAsState().value,
                        nextFiveDaysForecastState =
                        homeViewModel.nextFiveDaysForecast.collectAsState().value
                    )
                    isLocationGranted = true
                },
                onPermissionGranted = {
                    val locationUtil = LocationUtil(context) { location ->
                        homeViewModel.getCurrentWeather(
                            lat = location.latitude,
                            lng = location.longitude,
                            units = MeasurementUnit.METRIC.value
                        )

                        homeViewModel.listForecastIntent.value =
                            ListForecastIntent.GetNextFiveDaysForecast(
                                lat = location.latitude,
                                lng = location.longitude,
                                units = MeasurementUnit.METRIC.value
                            )

                    }
                    context.findActivity()?.let {
                        locationUtil.getCurrentLocation(it)
                    }
                }
            ) else
            HomeLayout(
                context = context,
                innerPadding = innerPadding,
                navigateToSearchCityScreen = navigateToSearchCityScreen,
                weatherState = homeViewModel.currentWeather.collectAsState().value,
                nextFiveDaysForecastState =
                homeViewModel.nextFiveDaysForecast.collectAsState().value
            )
    }
}


@Composable
fun HomeLayout(
    context: Context,
    innerPadding: PaddingValues,
    navigateToSearchCityScreen: () -> Unit,
    weatherState: UIState<Weather>,
    nextFiveDaysForecastState: UIState<List<Weather>>
) {
    if (context.isLargeScreen())
        HomeTabletLayout(
            innerPadding = innerPadding,
            navigateToSearchCityScreen = navigateToSearchCityScreen,
            weatherState = weatherState,
            nextFiveDaysForecastState = nextFiveDaysForecastState
        )
    else
        HomePhoneLayout(
            innerPadding = innerPadding,
            navigateToSearchCityScreen = navigateToSearchCityScreen,
            weatherState = weatherState,
            nextFiveDaysForecastState = nextFiveDaysForecastState
        )
}