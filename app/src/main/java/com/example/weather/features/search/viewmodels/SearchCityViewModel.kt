package com.example.weather.features.search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Weather
import com.example.domain.usecases.GetCityWeatherUseCase
import com.example.weather.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : ViewModel() {

    private val _cityName = MutableStateFlow<String>("")
    val cityName = _cityName.asStateFlow()

    private val _cityWeather = MutableStateFlow<UIState<Weather>>(UIState.Initial)
    val cityWeather = _cityWeather.asStateFlow()


    fun updateCityName(text: String) {
        _cityName.value = text
    }

    fun getCityWeather(
        units: String
    ) {
        viewModelScope.launch {
            _cityWeather.value = UIState.Loading
            _cityWeather.value = try {
                UIState.Data(
                    data = getCityWeatherUseCase(
                        cityName = _cityName.value,
                        units = units
                    )
                )
            } catch (e: Exception) {
                UIState.Error(error = e)
            }
        }
    }

}
