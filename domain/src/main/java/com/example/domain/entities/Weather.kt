package com.example.domain.entities

import java.util.Date

data class Weather(
    val date:Date?,
    val name: String?,
    val description:String?,
    val icon: String?,
    val temp: Double?
)