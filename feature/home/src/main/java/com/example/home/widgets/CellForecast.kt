package com.example.home.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.common.constants.Constants
import com.example.common.utils.DateUtil
import com.example.designsystem.R.dimen
import com.example.designsystem.component.CustomSubcomposeAsyncImage
import com.example.designsystem.theme.veryDarkGray
import com.example.domain.entities.Weather
import com.example.localization.R.string

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
                color = MaterialTheme.colorScheme.onPrimary,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSubcomposeAsyncImage(
                modifier = Modifier
                    .width(dimensionResource(dimen.view_size_48dp))
                    .height((dimensionResource(dimen.view_size_48dp))),
                data = "${Constants.WEATHER_CONDITION_ICON_BASE_URL}${weather.icon}.png",
                contentDescription = stringResource(string.weather_condition_icon)
            )
            Text(
                text = stringResource(string.celsius, weather.temp.toString()),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
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