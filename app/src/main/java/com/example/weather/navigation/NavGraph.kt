package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.screens.HomeScreen
import com.example.search.screans.SearchCityScreen
import com.example.weather.navigation.Screen.Home
import com.example.weather.navigation.Screen.SearchCity

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(navigateToSearchCityScreen = { navController.navigate(route = SearchCity) })
        }

        composable<SearchCity> {
            SearchCityScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}