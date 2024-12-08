package com.example.data.di

import com.example.data.remote.apis.WeatherAPI
import com.example.data.repositories.WeatherRepository
import com.example.domain.repositories.BaseWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideWeatherRepository(
        weatherAPI: WeatherAPI
    ): BaseWeatherRepository =
        WeatherRepository(
            weatherAPI = weatherAPI
        )
}