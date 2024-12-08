package com.example.weather.features.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.domain.entities.Weather
import com.example.weather.common.UIState
import com.example.weather.features.home.widgets.ListForecast
import com.example.weather.features.home.widgets.SectionSearchCity
import com.example.weather.features.home.widgets.SectionCurrentWeather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentWeatherState: UIState<Weather>,
    nextFiveDaysForecastState: UIState<List<Weather>>
) {
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            SectionSearchCity()
            SectionCurrentWeather(
                modifier = Modifier.fillMaxHeight(0.3f),
                currentWeatherState = currentWeatherState
            )
            ListForecast(nextFiveDaysForecastState = nextFiveDaysForecastState)
        }
    }
}