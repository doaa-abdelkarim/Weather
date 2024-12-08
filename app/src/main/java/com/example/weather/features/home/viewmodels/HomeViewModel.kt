package com.example.weather.features.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import com.example.weather.common.UIState
import com.example.weather.constants.enum.Unit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) : ViewModel() {

    private val _currentWeather = MutableStateFlow<UIState<Weather>>(UIState.Initial)
    val currentWeather = _currentWeather.asStateFlow()

    private val _nextFiveDaysForecast = MutableStateFlow<UIState<List<Weather>>>(UIState.Initial)
    val nextFiveDaysForecast = _nextFiveDaysForecast.asStateFlow()

    init {
        getCurrentWeather(
            lat = 44.34,
            lng = 10.99,
            units = Unit.METRIC.value
        )

        getNextFiveDaysForecast(
            lat = 44.34,
            lng = 10.99,
            units = Unit.METRIC.value
        )
    }

    private fun getCurrentWeather(
        lat: Double,
        lng: Double,
        units: String
    ) {
        viewModelScope.launch {
            _currentWeather.value = UIState.Loading
            try {
                _currentWeather.value = UIState.Data(
                    data = baseWeatherRepository.getCurrentWeather(
                        lat = lat,
                        lng = lng,
                        units = units
                    )
                )
            } catch (e: Exception) {
                _currentWeather.value = UIState.Error(error = e)
            }
        }
    }

    private fun getNextFiveDaysForecast(
        lat: Double,
        lng: Double,
        units: String
    ) {
        viewModelScope.launch {
            _nextFiveDaysForecast.value = UIState.Loading
            try {
                _nextFiveDaysForecast.value = UIState.Data(
                    data = baseWeatherRepository.getNextFiveDaysForecast(
                        lat = lat,
                        lng = lng,
                        units = units
                    )
                )
            } catch (e: Exception) {
                _nextFiveDaysForecast.value = UIState.Error(error = e)
            }
        }
    }

}
