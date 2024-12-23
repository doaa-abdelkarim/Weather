package com.example.designsystem.component

import androidx.annotation.DimenRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.example.designsystem.R

@Composable
fun ItemSkeleton(
    modifier: Modifier = Modifier,
    width: Float? = null,
    @DimenRes height: Int? = null,
    @DimenRes size: Int? = null,
    backgroundColor: Color = Color.LightGray,
    @DimenRes topStart: Int = R.dimen.rounded_corner_radius_4dp,
    @DimenRes topEnd: Int = R.dimen.rounded_corner_radius_4dp,
    @DimenRes bottomEnd: Int = R.dimen.rounded_corner_radius_4dp,
    @DimenRes bottomStart: Int = R.dimen.rounded_corner_radius_4dp,
    shape: Shape = RoundedCornerShape(
        topStart = dimensionResource(topStart),
        topEnd = dimensionResource(topEnd),
        bottomEnd = dimensionResource(bottomEnd),
        bottomStart = dimensionResource(bottomStart)
    )
) {
    Box(
        modifier = modifier
            .let {
                if (size == null) {
                    it
                        .fillMaxWidth(width ?: 0.0f)
                        .height(dimensionResource(height ?: R.dimen.zero))
                } else {
                    it.size(dimensionResource(size))
                }
            }
            .clip(shape)
            .background(backgroundColor)
    )
}
