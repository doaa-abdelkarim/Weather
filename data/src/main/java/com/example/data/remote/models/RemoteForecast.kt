package com.example.data.remote.models

import com.example.domain.entities.Weather
import com.google.gson.annotations.SerializedName

data class RemoteForecast(

    @field:SerializedName("city")
    val city: City? = null,

    @field:SerializedName("cnt")
    val cnt: Int? = null,

    @field:SerializedName("cod")
    val cod: String? = null,

    @field:SerializedName("message")
    val message: Int? = null,

    @field:SerializedName("list")
    val remoteWeatherList: List<RemoteWeather?>? = null
)

data class City(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("coord")
    val coord: Coord? = null,

    @field:SerializedName("sunrise")
    val sunrise: Int? = null,

    @field:SerializedName("timezone")
    val timezone: Int? = null,

    @field:SerializedName("sunset")
    val sunset: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("population")
    val population: Int? = null
)

fun RemoteForecast.asDomainModel(): List<Weather> =
    remoteWeatherList
        ?.asSequence()
        ?.filterNotNull()
        ?.map { it.asDomainModel() }
        ?.toList() ?: emptyList()

