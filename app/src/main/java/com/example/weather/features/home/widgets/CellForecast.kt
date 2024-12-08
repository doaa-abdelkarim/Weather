package com.example.weather.features.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.common.CustomSubcomposeAsyncImage
import com.example.weather.constants.AppConstants
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.veryDarkGray

@Composable
fun CellForecast(weather: Weather) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weather.time.toString(),
            style = TextStyle(
                fontSize = 18.sp,
                color = black,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSubcomposeAsyncImage(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.view_size_48dp))
                    .height((dimensionResource(R.dimen.view_size_48dp))),
                data = "${AppConstants.WEATHER_CONDITION_ICON_BASE_URL}${weather.icon}.png",
                contentDescription = stringResource(R.string.weather_condition_icon)
            )
            Text(
                text = weather.temp.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = black,
                )
            )
        }
        Text(
            text = weather.description ?: "-",
            style = TextStyle(
                fontSize = 12.sp,
                color = veryDarkGray,
            )
        )
    }
}