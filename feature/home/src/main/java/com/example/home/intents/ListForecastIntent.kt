package com.example.home.intents

sealed class ListForecastIntent {
    data object Idle: ListForecastIntent()
    data class GetNextFiveDaysForecast(
        val lat: Double,
        val lng: Double,
        val units: String
    ) : ListForecastIntent()
}