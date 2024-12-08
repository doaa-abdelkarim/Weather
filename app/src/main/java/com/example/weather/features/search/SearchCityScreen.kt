package com.example.weather.features.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.R
import com.example.weather.constants.enum.MeasurementUnit
import com.example.weather.features.home.widgets.SectionWeather
import com.example.weather.features.search.viewmodels.SearchCountryViewModel
import com.example.weather.features.search.widgets.SearchBar
import com.example.weather.ui.theme.black
import com.example.weather.ui.theme.strongPink
import com.example.weather.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen(
    searchCountryViewModel: SearchCountryViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    var isInputValid by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = topAppBarColors(
                    containerColor = white,
                    navigationIconContentColor = black
                ),
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(R.dimen.spacing_large),
                )
                .fillMaxSize(),
        ) {
            SearchBar(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.spacing_large)
                ),
                hint = stringResource(R.string.search_city),
                onSearch = { text -> searchCountryViewModel.updateCountryName(text = text) }
            )
            if (!isInputValid)
                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_small)),
                    text = stringResource(R.string.please_enter_country_name),
                    style = TextStyle(color = strongPink)
                )
            Button(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.spacing_normal))
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if (searchCountryViewModel.countryName.value.isEmpty()) {
                        isInputValid = false
                    } else {
                        isInputValid = true
                        searchCountryViewModel.getCountryWeather(
                            units = MeasurementUnit.METRIC.value
                        )
                    }
                }
            ) {
                Text(stringResource(R.string.search))
            }
            SectionWeather(
                modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_xlarge)),
                weatherState = searchCountryViewModel.countryWeather.collectAsState().value
            )
        }
    }
}