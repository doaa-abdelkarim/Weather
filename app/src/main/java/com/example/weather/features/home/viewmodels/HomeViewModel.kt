package com.example.weather.features.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.extensions.exhaustive
import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import com.example.weather.common.UIState
import com.example.weather.constants.Constants.PATTERN_YYYYMMDD
import com.example.weather.features.home.intents.ListForecastIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) : ViewModel() {

    private val _currentWeather = MutableStateFlow<UIState<Weather>>(UIState.Initial)
    val currentWeather = _currentWeather.asStateFlow()

    val listForecastIntent = MutableStateFlow<ListForecastIntent>(ListForecastIntent.Idle)

    private val _nextFiveDaysForecast = MutableStateFlow<UIState<List<Weather>>>(UIState.Initial)
    val nextFiveDaysForecast = _nextFiveDaysForecast.asStateFlow()

    init {
        processIntent()
    }

    fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ) {
        viewModelScope.launch {
            _currentWeather.value = UIState.Loading
            _currentWeather.value = try {
                UIState.Data(
                    data = baseWeatherRepository.getCurrentWeather(
                        lat = lat,
                        lng = lng,
                        units = units
                    )
                )
            } catch (e: Exception) {
                UIState.Error(error = e)
            }
        }
    }

    private fun processIntent() {
        viewModelScope.launch {
            _nextFiveDaysForecast.value = UIState.Loading
            listForecastIntent.collect {
                when (it) {
                    is ListForecastIntent.Idle -> {}
                    is ListForecastIntent.GetNextFiveDaysForecast ->
                        _nextFiveDaysForecast.value = try {
                            UIState.Data(
                                data = baseWeatherRepository.getNextFiveDaysForecast(
                                    lat = it.lat,
                                    lng = it.lng,
                                    units = it.units
                                ).groupBy { weather ->
                                    weather.date?.let { date ->
                                        SimpleDateFormat(PATTERN_YYYYMMDD, Locale.getDefault())
                                            .format(date)
                                    }
                                } // Group by the date part only
                                    .map { entry ->
                                        entry.value.first()
                                    } // Pick the first object for each unique date
                            )
                        } catch (e: Exception) {
                            UIState.Error(error = e)
                        }
                }.exhaustive
            }
        }
    }

}
