package com.example.home.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.designsystem.R
import com.example.designsystem.component.ItemSkeleton
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonCellForecast(
    modifier: Modifier = Modifier,
    shimmerInstance: Shimmer
) {
    Row(
        modifier = modifier
            .shimmer(shimmerInstance)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemSkeleton(
            modifier = Modifier.weight(0.4f),
            height = R.dimen.view_size_35dp
        )
        Row(
            modifier = Modifier
                .weight(0.3f)
                .padding(start = dimensionResource(R.dimen.spacing_normal)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemSkeleton(
                size = R.dimen.view_size_30dp,
                shape = CircleShape
            )
            ItemSkeleton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.spacing_small)),
                height = R.dimen.view_size_25dp
            )
        }
        ItemSkeleton(
            modifier = Modifier
                .weight(0.25f)
                .padding(start = dimensionResource(R.dimen.spacing_normal)),
            height = R.dimen.view_size_15dp
        )
    }
}