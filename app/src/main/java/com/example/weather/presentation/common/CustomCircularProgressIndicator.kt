package com.example.weather.presentation.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.weather.ui.theme.softRed

@Composable
fun CustomCircularProgressIndicator() {
    CircularProgressIndicator(color = softRed)
}