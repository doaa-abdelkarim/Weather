package com.example.weather.presentation.features.search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Weather
import com.example.domain.usecases.GetCityWeatherUseCase
import com.example.weather.presentation.common.UIState
import com.example.weather.presentation.constants.enum.MeasurementUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : ViewModel() {

    private val _cityName = MutableStateFlow("")
    val cityName = _cityName.asStateFlow()

    private val _cityWeather = MutableStateFlow<UIState<Weather>>(UIState.Initial)
    val cityWeather = _cityWeather.asStateFlow()

    init {
        getCityWeather(units = MeasurementUnit.METRIC.value)
    }

    fun updateCityName(text: String) {
        _cityName.value = text
    }

    fun getCityWeather(
        units: String
    ) {
        viewModelScope.launch {
            _cityWeather.value = UIState.Loading
            try {
                getCityWeatherUseCase(
                    cityName = _cityName.value,
                    units = units
                ).distinctUntilChanged()
                    .collectLatest {
                        _cityWeather.value = UIState.Data(data = it)
                    }
            } catch (e: Exception) {
                _cityWeather.value = UIState.Error(error = e)
            }
        }
    }

}
