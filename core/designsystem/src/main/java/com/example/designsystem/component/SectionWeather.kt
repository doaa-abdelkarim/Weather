package com.example.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import com.example.common.constants.Constants.WEATHER_CONDITION_ICON_BASE_URL
import com.example.common.constants.UIState
import com.example.common.utils.DateUtil
import com.example.designsystem.R.dimen
import com.example.designsystem.theme.softRed
import com.example.domain.entities.Weather
import com.example.localization.R.string

@Composable
fun SectionWeather(
    modifier: Modifier = Modifier,
    weatherState: UIState<Weather>,
    shouldShowProgressIndicator: Boolean
) {
    when (weatherState) {
        is UIState.Initial -> {
            if (shouldShowProgressIndicator)
                SkeletonSectionWeather(
                    modifier = modifier.fillMaxSize(),
                    shouldShowProgressIndicator = shouldShowProgressIndicator
                )
        }

        is UIState.Loading -> {
            SkeletonSectionWeather(
                modifier = modifier.fillMaxSize(),
                shouldShowProgressIndicator = shouldShowProgressIndicator
            )
        }

        is UIState.Data -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    weatherState.data.date?.let { DateUtil.convertDateToString(it) } ?: "",
                    style = TextStyle(
                        fontSize = 21.sp,
                        color = softRed
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = dimensionResource(dimen.spacing_small)
                    ),
                    text = weatherState.data.name ?: "",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = dimensionResource(dimen.spacing_small)
                    ),
                    text = weatherState.data.description ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (weatherState.data.icon != null)
                        CustomSubcomposeAsyncImage(
                            modifier = Modifier
                                .width(dimensionResource(dimen.view_size_64dp))
                                .height((dimensionResource(dimen.view_size_64dp))),
                            data = "$WEATHER_CONDITION_ICON_BASE_URL${weatherState.data.icon}.png",
                            contentDescription = stringResource(string.weather_condition_icon)
                        )
                    Text(
                        text = weatherState.data.temp?.let {
                            stringResource(
                                string.celsius,
                                weatherState.data.temp.toString()
                            )
                        } ?: "",
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
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
                    text = weatherState.error.localizedMessage ?: stringResource(
                        string.unknown_error
                    ),
                    style = TextStyle(color = MaterialTheme.colorScheme.onError),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}