package com.example.data.remote.models

import com.example.domain.entities.Weather
import com.google.gson.annotations.SerializedName

data class RemoteForecast(

    @field:SerializedName("city")
    val city: City? = null,

    @field:SerializedName("list")
    val remoteWeatherList: List<RemoteWeather?>? = null
)

data class City(

    @field:SerializedName("name")
    val name: String? = null
)

fun RemoteForecast.asDomainModel(): List<Weather> =
    remoteWeatherList
        ?.asSequence()
        ?.filterNotNull()
        ?.map { it.asDomainModel() }
        ?.toList() ?: emptyList()

