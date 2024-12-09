package com.example.weather.presentation.common

sealed class UIState<out T> {
    data object Initial : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Data<T>(val data: T) : UIState<T>()
    data class Error(val error: Throwable) : UIState<Nothing>()
}