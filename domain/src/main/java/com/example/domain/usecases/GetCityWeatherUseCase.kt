package com.example.domain.usecases

import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) {
    suspend operator fun invoke(
        cityName: String,
        units: String
    ): Flow<Weather> {
        return baseWeatherRepository.getCityWeather(
            cityName = cityName,
            units = units
        )
    }
}