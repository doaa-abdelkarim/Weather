package com.example.weather.features.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.core.utils.DateUtil
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.common.CustomCircularProgressIndicator
import com.example.weather.common.CustomSubcomposeAsyncImage
import com.example.weather.common.UIState
import com.example.weather.constants.Constants
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.softRed
import com.example.weather.ui.theme.strongPink

@Composable
fun SectionCurrentWeather(
    modifier: Modifier,
    currentWeatherState: UIState<Weather>
) {
    when (currentWeatherState) {
        is UIState.Initial, UIState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CustomCircularProgressIndicator()
            }
        }

        is UIState.Data -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    currentWeatherState.data.date?.let { DateUtil.convertDateToString(it) } ?: "-",
                    style = TextStyle(
                        fontSize = 21.sp,
                        color = softRed
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.spacing_small)
                    ),
                    text = currentWeatherState.data.name ?: "-",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.spacing_small)
                    ),
                    text = currentWeatherState.data.description ?: "-",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomSubcomposeAsyncImage(
                        modifier = Modifier
                            .width(dimensionResource(R.dimen.view_size_64dp))
                            .height((dimensionResource(R.dimen.view_size_64dp))),
                        data = "${Constants.WEATHER_CONDITION_ICON_BASE_URL}${currentWeatherState.data.icon}.png",
                        contentDescription = stringResource(R.string.weather_condition_icon)
                    )
                    Text(
                        text = stringResource(
                            R.string.celsius,
                            currentWeatherState.data.temp.toString()
                        ),
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = black,
                        )
                    )
                }
            }
        }

        is UIState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = currentWeatherState.error.localizedMessage ?: stringResource(
                        R.string.unknown_error
                    ),
                    style = TextStyle(color = strongPink),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}