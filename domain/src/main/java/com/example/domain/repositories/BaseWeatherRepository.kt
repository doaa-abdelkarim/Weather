package com.example.domain.repositories

import com.example.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface BaseWeatherRepository {
    suspend fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ): Weather

    suspend fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ): List<Weather>

    suspend fun getCityWeather(
        cityName: String,
        units: String
    ): Flow<Weather>
}