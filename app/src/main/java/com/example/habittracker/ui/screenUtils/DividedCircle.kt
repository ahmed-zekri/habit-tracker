package com.example.habittracker.ui.screenUtils

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun DividedCircle(
    modifier: Modifier,
    arcsNumber: Int,
    arcsPadding: Float = 30f,
    arcStartingAngle: Int = 77,
    arcWidth: Float = 10f
) {
    Canvas(
        modifier = modifier
    ) {
        (1..arcsNumber).forEach { index ->
            drawArc(
                Color.White,
                arcStartingAngle - ((360f / arcsNumber) * index) + if (arcsNumber > 1) arcsPadding * (2f / arcsNumber) else 0f,
                (360f / arcsNumber) - if (arcsNumber > 1) arcsPadding * (2f / arcsNumber) else 0f,
                false,
                style = Stroke(
                    width = arcWidth
                )
            )
        }

    }


}