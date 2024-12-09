package com.example.data.repositories

import com.example.data.local.datasources.BaseWeatherLocaleDataSource
import com.example.data.local.models.asDomainModel
import com.example.data.remote.datasources.BaseWeatherRemoteDataSource
import com.example.data.remote.models.asDomainModel
import com.example.data.remote.models.asLocalModel
import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepository(
    private val baseWeatherRemoteDatasource: BaseWeatherRemoteDataSource,
    private val baseWeatherLocaleDatasource: BaseWeatherLocaleDataSource
) : BaseWeatherRepository {

    override suspend fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ): Weather {
        return baseWeatherRemoteDatasource.getCurrentWeather(
            lat = lat,
            lng = lng,
            units = units
        ).asDomainModel()
    }

    override suspend fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ): List<Weather> {
        return baseWeatherRemoteDatasource.getNextFiveDaysForecast(
            lat = lat,
            lng = lng,
            units = units
        ).asDomainModel()
    }

    override suspend fun getCityWeather(
        cityName: String,
        units: String
    ): Flow<Weather> {
        if(cityName.isNotBlank()) {
            val result = baseWeatherRemoteDatasource.getCityWeather(
                cityName = cityName,
                units = units
            )
            baseWeatherLocaleDatasource.cacheCityWeather(localWeather = result.asLocalModel())
        }
        return baseWeatherLocaleDatasource.preferencesFlow.map {
            it.asDomainModel()
        }
    }
}

