package com.example.data.remote.datasources

import com.example.data.remote.apis.WeatherAPI
import com.example.data.remote.models.RemoteForecast
import com.example.data.remote.models.RemoteWeather

interface BaseWeatherRemoteDataSource {
    suspend fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ): RemoteWeather

    suspend fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ): RemoteForecast

    suspend fun getCityWeather(
        cityName: String,
        units: String
    ): RemoteWeather
}

class WeatherRemoteDataSource(private val weatherAPI: WeatherAPI) : BaseWeatherRemoteDataSource {
    override suspend fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ): RemoteWeather {
        return weatherAPI.getCurrentWeather(
            lat = lat,
            lng = lng,
            units = units
        )
    }

    override suspend fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ): RemoteForecast {
        return weatherAPI.getNextFiveDaysForecast(
            lat = lat,
            lng = lng,
            units = units
        )
    }

    override suspend fun getCityWeather(
        cityName: String,
        units: String
    ): RemoteWeather {
        return weatherAPI.getCityWeather(
            cityName = cityName,
            units = units
        )
    }
}