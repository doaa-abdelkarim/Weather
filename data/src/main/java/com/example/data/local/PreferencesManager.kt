package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.local.models.LocalWeather
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PreferencesManager"

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    val preferencesFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.tag(TAG).e(exception, "Error reading preferences")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            LocalWeather(
                date = preferences[PreferencesKeys.DATE],
                name = preferences[PreferencesKeys.NAME],
                description = preferences[PreferencesKeys.DESCRIPTION],
                icon = preferences[PreferencesKeys.ICON],
                temp = preferences[PreferencesKeys.TEMP],
            )
        }

    suspend fun updateLastSearchedCityWeather(localWeather: LocalWeather) {
        context.dataStore.edit { preferences ->
            localWeather.date?.let { preferences[PreferencesKeys.DATE] = it }
            localWeather.name?.let { preferences[PreferencesKeys.NAME] = it }
            localWeather.description?.let { preferences[PreferencesKeys.DESCRIPTION] = it }
            localWeather.icon?.let { preferences[PreferencesKeys.ICON] = it }
            localWeather.temp?.let { preferences[PreferencesKeys.TEMP] = it }
        }
    }

    private object PreferencesKeys {
        val DATE = longPreferencesKey("date")
        val NAME = stringPreferencesKey("name")
        val DESCRIPTION = stringPreferencesKey("description")
        val ICON = stringPreferencesKey("icon")
        val TEMP = doublePreferencesKey("temp")
    }
}