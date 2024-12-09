package com.example.data.remote.models

import com.example.core.utils.DateUtil
import com.example.data.local.models.LocalWeather
import com.example.domain.entities.Weather
import com.google.gson.annotations.SerializedName

data class RemoteWeather(

    @field:SerializedName("main")
    val main: Main? = null,

    @field:SerializedName("dt")
    val dt: Long? = null,

    @field:SerializedName("weather")
    val weather: List<WeatherItem?>? = null,

    @field:SerializedName("name")
    val name: String? = null

)

data class Main(

    @field:SerializedName("temp")
    val temp: Double? = null
)

data class WeatherItem(

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("description")
    val description: String? = null
)

fun RemoteWeather.asLocalModel(): LocalWeather =
    LocalWeather(
        date = dt?.let { dt * 1000 },
        name = name,
        description = weather?.get(0)?.description,
        icon = weather?.get(0)?.icon,
        temp = main?.temp
    )

fun RemoteWeather.asDomainModel(): Weather =
    Weather(
        date = dt?.let { DateUtil.convertLongToDate(dt * 1000) },
        name = name,
        description = weather?.get(0)?.description,
        icon = weather?.get(0)?.icon,
        temp = main?.temp
    )
