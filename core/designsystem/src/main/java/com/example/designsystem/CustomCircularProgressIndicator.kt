package com.example.designsystem

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.weather.ui.theme.softRed

@Composable
fun CustomCircularProgressIndicator() {
    CircularProgressIndicator(color = softRed)
}