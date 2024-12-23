package com.example.search.screans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.common.constants.UIState
import com.example.designsystem.R.dimen
import com.example.designsystem.component.ItemInvalidInput
import com.example.designsystem.component.SectionWeather
import com.example.domain.entities.Weather
import com.example.localization.R.string
import com.example.search.widgets.SearchBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchCityPhoneLayout(
    innerPadding: PaddingValues,
    cityName: StateFlow<String>,
    onSearchTapped: () -> Unit,
    weatherState: UIState<Weather>,
    text: String,
    onValueChange: (String) -> Unit
) {
    var isInputValid by rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(
                horizontal = dimensionResource(dimen.spacing_large),
            )
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(
                top = dimensionResource(dimen.spacing_large)
            ),
            hint = stringResource(string.search_city),
            text = text,
            onValueChange = onValueChange
        )
        if (!isInputValid)
            ItemInvalidInput(
                modifier = Modifier.padding(top = dimensionResource(dimen.spacing_small)),
                text = stringResource(string.please_enter_city_name)
            )
        Button(
            modifier = Modifier
                .padding(top = dimensionResource(dimen.spacing_normal))
                .align(Alignment.CenterHorizontally),
            onClick = {
                if (cityName.value.isBlank()) {
                    isInputValid = false
                } else {
                    isInputValid = true
                    onSearchTapped()
                }
            }
        ) {
            Text(stringResource(string.search))
        }
        Text(
            modifier = Modifier.padding(top = dimensionResource(dimen.spacing_large)),
            text = stringResource(string.last_searched_city),
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        )
        SectionWeather(
            modifier = Modifier.padding(top = dimensionResource(dimen.spacing_xlarge)),
            weatherState = weatherState,
            shouldShowProgressIndicator = false
        )
    }
}