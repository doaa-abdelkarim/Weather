package com.example.domain.entities

data class Weather(
    val time:Long?,
    val name: String?,
    val description:String?,
    val icon: String?,
    val temp: Double?
)