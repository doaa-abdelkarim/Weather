package com.example.data.remote.apis

import com.example.data.remote.models.RemoteForecast
import com.example.data.remote.models.RemoteWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("units") units: String
    ): RemoteWeather

    @GET("forecast")
    suspend fun getNextFiveDaysForecast(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("units") units: String
    ): RemoteForecast

    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("units") units: String
    ): RemoteWeather
}