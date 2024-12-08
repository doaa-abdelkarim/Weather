package com.example.weather.features.search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Weather
import com.example.domain.repositories.BaseWeatherRepository
import com.example.weather.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCountryViewModel @Inject constructor(
    private val baseWeatherRepository: BaseWeatherRepository,
) : ViewModel() {

    private val _countryName = MutableStateFlow<String>("")
    val countryName = _countryName.asStateFlow()

    private val _countryWeather = MutableStateFlow<UIState<Weather>>(UIState.Initial)
    val countryWeather = _countryWeather.asStateFlow()


    fun updateCountryName(text: String) {
        _countryName.value = text
    }

    fun getCountryWeather(
        units: String
    ) {
        viewModelScope.launch {
            _countryWeather.value = UIState.Loading
            _countryWeather.value = try {
                UIState.Data(
                    data = baseWeatherRepository.getCountryWeather(
                        countryName = _countryName.value,
                        units = units
                    )
                )
            } catch (e: Exception) {
                UIState.Error(error = e)
            }
        }
    }

}
