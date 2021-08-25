package com.flower.base.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flower.base.utils.TAG_PROGRESS_INDICATOR
import com.flower.theme.Black

@Composable
fun FlowerSpotCircularProgressIndicator(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    color: Color = Black,
    strokeWidth: Dp = 2.dp
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier.testTag(TAG_PROGRESS_INDICATOR),
            color = color, strokeWidth = strokeWidth
        )
    } else {
        Spacer(Modifier.height(0.dp))
    }
}
