package com.example.data.local.models

import com.example.core.utils.DateUtil
import com.example.domain.entities.Weather

data class LocalWeather(
    val date: Long?,
    val name: String?,
    val description: String?,
    val icon: String?,
    val temp: Double?
)

fun LocalWeather.asDomainModel(): Weather =
    Weather(
        date = date?.let { DateUtil.convertLongToDate(time = it) },
        name = name,
        description = description,
        icon = icon,
        temp = temp
    )