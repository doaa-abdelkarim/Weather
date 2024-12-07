package com.example.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Weather

@Entity(
    tableName = "weather_table"
)
data class LocalWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)

fun LocalWeather.asDomainModel(): Weather =
    Weather()