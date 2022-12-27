package com.example.habittracker.ui.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DoubleColorCircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    stroke: Dp,
    colorProgress: Color,
    colorRemaining: Color,
    size: Dp = 150.dp
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            progress = progress,
            color = colorProgress,
            strokeWidth = stroke, modifier = Modifier.size(size = size)
        )
        CircularProgressIndicator(
            progress = 1f - progress,
            modifier = Modifier
                .scale(scaleX = -1f, scaleY = 1f)
                .size(size = size),
            color = colorRemaining,
            strokeWidth = stroke
        )
    }
}