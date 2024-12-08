package com.example.weather.features.home.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.core.utils.DateUtil
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.common.CustomSubcomposeAsyncImage
import com.example.weather.constants.Constants
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.veryDarkGray

@Composable
fun CellForecast(weather: Weather) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.4f),
            text = weather.date?.let { DateUtil.convertDateToString(it) } ?: "-",
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
                data = "${Constants.WEATHER_CONDITION_ICON_BASE_URL}${weather.icon}.png",
                contentDescription = stringResource(R.string.weather_condition_icon)
            )
            Text(
                text = stringResource(R.string.celsius, weather.temp.toString()),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = black,
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = weather.description ?: "-",
            style = TextStyle(
                fontSize = 14.sp,
                color = veryDarkGray,
            )
        )
    }
}