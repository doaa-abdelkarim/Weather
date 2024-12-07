package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.db.WeatherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, WeatherDB::class.java, "weather_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideWeatherDao(db: WeatherDB) = db.weatherDao()

}