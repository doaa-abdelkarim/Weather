package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.features.home.screens.HomeScreen
import com.example.weather.navigation.Screen.Home

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen()
        }
    }
}