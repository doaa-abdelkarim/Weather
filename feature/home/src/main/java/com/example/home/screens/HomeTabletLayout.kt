package com.example.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.common.constants.UIState
import com.example.designsystem.SectionWeather
import com.example.domain.entities.Weather
import com.example.home.widgets.ListForecast
import com.example.home.widgets.SectionSearchCity
import com.example.localization.R

@Composable
fun HomeTabletLayout(
    innerPadding: PaddingValues,
    navigateToSearchCityScreen: () -> Unit,
    weatherState: UIState<Weather>,
    nextFiveDaysForecastState: UIState<List<Weather>>
) {
    Row(
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                start = dimensionResource(R.dimen.spacing_small),
                top = dimensionResource(R.dimen.spacing_large),
                end = dimensionResource(R.dimen.spacing_small),
            )
            .fillMaxSize(),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            SectionSearchCity(navigateToSearchCityScreen = navigateToSearchCityScreen)
            Spacer(modifier = Modifier.weight(1f))
            SectionWeather(
                weatherState = weatherState,
                shouldShowProgressIndicator = true
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ListForecast(
                nextFiveDaysForecastState = nextFiveDaysForecastState
            )
        }
    }
}