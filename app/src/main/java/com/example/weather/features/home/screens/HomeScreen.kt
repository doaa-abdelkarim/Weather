package com.example.weather.features.home.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.extensions.findActivity
import com.example.core.utils.LocationUtil
import com.example.weather.R
import com.example.weather.common.RequestPermission
import com.example.weather.constants.enum.Unit
import com.example.weather.features.home.intents.ListForecastIntent
import com.example.weather.features.home.viewmodels.HomeViewModel
import com.example.weather.features.home.widgets.ListForecast
import com.example.weather.features.home.widgets.SectionCurrentWeather
import com.example.weather.features.home.widgets.SectionSearchCity
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = topAppBarColors(
                    containerColor = White,
                )
            )
        }
    ) { innerPadding ->
        RequestPermission(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            initialContent = { onRequestPermission ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp),
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
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                ) {
                    SectionSearchCity()
                    SectionCurrentWeather(
                        modifier = Modifier.fillMaxHeight(0.3f),
                        currentWeatherState = homeViewModel.currentWeather.collectAsState().value
                    )
                    ListForecast(
                        nextFiveDaysForecastState =
                        homeViewModel.nextFiveDaysForecast.collectAsState().value
                    )
                }
            },
            onPermissionGranted = {
                val locationUtil = LocationUtil(context) { location ->
                    homeViewModel.getCurrentWeather(
                        lat = location.latitude,
                        lng = location.longitude,
                        units = Unit.METRIC.value
                    )

                    homeViewModel.listForecastIntent.value =
                        ListForecastIntent.GetNextFiveDaysForecast(
                            lat = location.latitude,
                            lng = location.longitude,
                            units = Unit.METRIC.value
                        )

                }
                context.findActivity()?.let {
                    locationUtil.getCurrentLocation(it)
                }
            }
        )
    }
}