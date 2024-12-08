package com.example.data.repositories

import com.example.data.remote.apis.WeatherAPI
import com.example.data.remote.models.asDomainModel
import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository

class WeatherRepository(
    private val weatherAPI: WeatherAPI
) : BaseWeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ): Weather {
        return weatherAPI.getCurrentWeather(
            lat = lat,
            lng = lng,
            units = units
        ).asDomainModel()
    }

    override suspend fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ): List<Weather> {
        return weatherAPI.getNextFiveDaysForecast(
            lat = lat,
            lng = lng,
            units = units
        ).asDomainModel()
    }

    override suspend fun getCountryWeather(
        countryName: String,
        units: String
    ): Weather {
        return weatherAPI.getCountryWeather(
            countryName = countryName,
            units = units
        ).asDomainModel()
    }
}

