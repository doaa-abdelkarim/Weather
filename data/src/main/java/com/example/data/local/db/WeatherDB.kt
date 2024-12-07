package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.db.dao.WeatherDao
import com.example.data.local.models.LocalWeather

@Database(
    entities = [
        LocalWeather::class
    ], version = 1
)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}