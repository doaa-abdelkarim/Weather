package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.features.home.screens.HomeScreen
import com.example.weather.features.home.viewmodels.HomeViewModel
import com.example.weather.navigation.Screen.Home

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val currentWeatherState by homeViewModel.currentWeather.collectAsState()
            val nextFiveDaysForecastState by homeViewModel.nextFiveDaysForecast.collectAsState()
            HomeScreen(
                currentWeatherState = currentWeatherState,
                nextFiveDaysForecastState = nextFiveDaysForecastState
            )
        }
    }
}