package com.example.habittracker.ui.screenUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    circleColor: Color,
    icon: ImageVector,
    padding: Dp = 15.dp,
    iconSize: Dp = 30.dp,
    iconTint: Color = Color.White
) {


    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier
            .background(circleColor, CircleShape)
            .padding(padding)
            .size(iconSize),
        tint = iconTint
    )
}
