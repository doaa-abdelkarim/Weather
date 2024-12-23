package com.example.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import com.example.designsystem.R
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.unclippedBoundsInWindow

@Composable
fun SkeletonListForeCast() {
    val shimmerInstance = rememberShimmer(ShimmerBounds.Custom)
    LazyColumn(
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
            // Util function included in the library
            val position = layoutCoordinates.unclippedBoundsInWindow()
            shimmerInstance.updateBounds(position)
        },
        contentPadding = PaddingValues(
            horizontal = dimensionResource(R.dimen.spacing_normal),
            vertical = dimensionResource(R.dimen.spacing_small)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_normal)),
    ) {
        items(30) { _ ->
            SkeletonCellForecast(shimmerInstance = shimmerInstance)
        }
    }
}