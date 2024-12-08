package com.example.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()

    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}