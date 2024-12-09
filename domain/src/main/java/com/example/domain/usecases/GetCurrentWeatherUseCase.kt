package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) {
    suspend operator fun invoke(
        lat: Double,
        lng: Double,
        units: String
    ): Weather {
        return baseWeatherRepository.getCurrentWeather(
            lat = lat,
            lng = lng,
            units = units
        )
    }

}