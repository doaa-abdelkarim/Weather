package com.example.weather.features.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.blue

@Composable
fun SectionSearchCity(
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(R.dimen.spacing_small),
            )
            .clickable { },
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            tint = blue,
            contentDescription = stringResource(R.string.search)
        )
        Text(
            text = stringResource(R.string.search_city),
            style = TextStyle(
                fontSize = 16.sp,
                color = blue,
                textDecoration = TextDecoration.Underline
            )
        )
    }

}