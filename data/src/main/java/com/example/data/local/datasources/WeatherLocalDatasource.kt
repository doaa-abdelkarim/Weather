package com.example.data.local.datasources

import com.example.data.local.PreferencesManager
import com.example.data.local.models.LocalWeather
import javax.inject.Inject

abstract class BaseWeatherLocaleDataSource(preferencesManager: PreferencesManager) {
    val preferencesFlow = preferencesManager.preferencesFlow

    abstract suspend fun cacheCityWeather(
        localWeather: LocalWeather
    )
}

class WeatherLocalDataSource @Inject constructor(private val preferencesManager: PreferencesManager) :
    BaseWeatherLocaleDataSource(preferencesManager = preferencesManager) {

    override suspend fun cacheCityWeather(localWeather: LocalWeather) {
        preferencesManager.updateLastSearchedCityWeather(localWeather = localWeather)
    }
}
