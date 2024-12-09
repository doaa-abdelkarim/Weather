package com.example.weather.presentation.features.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.presentation.common.UIState
import com.example.weather.presentation.features.home.widgets.ListForecast
import com.example.weather.presentation.features.home.widgets.SectionSearchCity
import com.example.weather.presentation.features.home.widgets.SectionWeather

@Composable
fun HomePhoneLayout(
    innerPadding: PaddingValues,
    navigateToSearchCityScreen: () -> Unit,
    weatherState: UIState<Weather>,
    nextFiveDaysForecastState: UIState<List<Weather>>
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(top = dimensionResource(R.dimen.spacing_large))
            .fillMaxSize(),
    ) {
        SectionSearchCity(navigateToSearchCityScreen = navigateToSearchCityScreen)
        SectionWeather(
            modifier = Modifier.fillMaxHeight(0.3f),
            weatherState = weatherState,
            shouldShowProgressIndicator = true
        )
        ListForecast(
            nextFiveDaysForecastState = nextFiveDaysForecastState
        )
    }
}