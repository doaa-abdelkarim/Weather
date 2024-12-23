package com.example.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.designsystem.R
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonSectionWeather(
    modifier: Modifier = Modifier,
    shouldShowProgressIndicator: Boolean
) {
    Column(
        modifier = modifier.shimmer(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (shouldShowProgressIndicator) Arrangement.Center else Arrangement.Top
    ) {
        ItemSkeleton(
            width = 0.3f,
            height = R.dimen.view_size_25dp
        )
        ItemSkeleton(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_normal)),
            width = 0.55f,
            height = R.dimen.view_size_35dp
        )
        ItemSkeleton(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_normal)),
            width = 0.1f,
            height = R.dimen.view_size_15dp
        )
        Row(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_normal)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemSkeleton(
                size = R.dimen.view_size_35dp,
                shape = CircleShape
            )
            ItemSkeleton(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.spacing_small)),
                width = 0.4f,
                height = R.dimen.view_size_30dp
            )
        }
    }
}