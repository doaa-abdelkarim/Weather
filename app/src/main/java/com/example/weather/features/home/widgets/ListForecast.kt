package com.example.weather.features.home.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.domain.entities.Weather
import com.example.weather.R
import com.example.weather.common.CustomCircularProgressIndicator
import com.example.weather.common.UIState
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.strongPink

@Composable
fun ListForecast(
    nextFiveDaysForecastState: UIState<List<Weather>>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.spacing_small),
                bottom = dimensionResource(R.dimen.spacing_small),
            ),
            text = stringResource(R.string.five_day_forecast),
            style = TextStyle(
                fontSize = 24.sp,
                color = black,
                fontWeight = FontWeight.Bold
            )
        )
        when (nextFiveDaysForecastState) {
            is UIState.Initial, UIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CustomCircularProgressIndicator()
                }
            }

            is UIState.Data -> {
                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = dimensionResource(R.dimen.spacing_normal),
                        vertical = dimensionResource(R.dimen.spacing_small)
                    )
                ) {
                    items(nextFiveDaysForecastState.data.size) { index ->
                        CellForecast(weather = nextFiveDaysForecastState.data.get(index))
                    }
                }
            }

            is UIState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = nextFiveDaysForecastState.error.localizedMessage ?: stringResource(
                            R.string.unknown_error
                        ),
                        style = TextStyle(color = strongPink),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}