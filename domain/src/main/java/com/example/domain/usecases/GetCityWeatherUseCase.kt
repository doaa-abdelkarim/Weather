package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) {
    suspend operator fun invoke(
        cityName: String,
        units: String
    ): Weather {
        return baseWeatherRepository.getCityWeather(
            cityName = cityName,
            units = units
        )
    }
}