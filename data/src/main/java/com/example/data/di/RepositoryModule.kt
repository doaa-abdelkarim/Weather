package com.example.data.di

import com.example.data.local.PreferencesManager
import com.example.data.local.datasources.BaseWeatherLocaleDataSource
import com.example.data.local.datasources.WeatherLocalDataSource
import com.example.data.remote.apis.WeatherAPI
import com.example.data.remote.datasources.BaseWeatherRemoteDataSource
import com.example.data.remote.datasources.WeatherRemoteDataSource
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
        baseWeatherRemoteDatasource: BaseWeatherRemoteDataSource,
        baseWeatherLocaleDatasource: BaseWeatherLocaleDataSource
    ): BaseWeatherRepository =
        WeatherRepository(
            baseWeatherRemoteDatasource = baseWeatherRemoteDatasource,
            baseWeatherLocaleDatasource = baseWeatherLocaleDatasource
        )

    @Provides
    fun provideWeatherRemoteDatasource(
        weatherAPI: WeatherAPI
    ): BaseWeatherRemoteDataSource = WeatherRemoteDataSource(
        weatherAPI = weatherAPI
    )

    @Provides
    fun provideWeatherLocaleDatasource(
        preferencesManager: PreferencesManager
    ): BaseWeatherLocaleDataSource = WeatherLocalDataSource(
        preferencesManager = preferencesManager
    )
}