package com.example.weather.presentation.features.search.screans

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.extensions.isLargeScreen
import com.example.weather.presentation.constants.enum.MeasurementUnit
import com.example.weather.presentation.features.search.viewmodels.SearchCityViewModel
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen(
    searchCountryViewModel: SearchCityViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (LocalContext.current.isLargeScreen())
            SearchCityTabletLayout(
                innerPadding = innerPadding,
                cityName = searchCountryViewModel.cityName,
                onValueChange = { text -> searchCountryViewModel.updateCityName(text = text) },
                onSearchTapped = {
                    searchCountryViewModel.getCityWeather(
                        units = MeasurementUnit.METRIC.value
                    )
                },
                text = searchCountryViewModel.cityName.collectAsState().value,
                weatherState = searchCountryViewModel.cityWeather.collectAsState().value
            )
        else
            SearchCityPhoneLayout(
                innerPadding = innerPadding,
                cityName = searchCountryViewModel.cityName,
                onValueChange = { text -> searchCountryViewModel.updateCityName(text = text) },
                onSearchTapped = {
                    searchCountryViewModel.getCityWeather(
                        units = MeasurementUnit.METRIC.value
                    )
                },
                text = searchCountryViewModel.cityName.collectAsState().value,
                weatherState = searchCountryViewModel.cityWeather.collectAsState().value,
            )
    }
}