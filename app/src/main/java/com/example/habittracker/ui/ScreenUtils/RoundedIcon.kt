package com.example.habittracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun RoundedIcon(
    modifier: Modifier,
    circleColor: Color,
    icon: ImageVector,
    iconTint: Color = Color.White
) {

    Box(
        modifier = modifier
            .background(
                color = circleColor,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.Center
            ), tint = iconTint
        )
    }
}