package com.example.habittracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.R

@Composable
fun ScreenTopBar(
    icon: ImageVector,
    text: String,
    iconSize: Dp? = null,
    onIconClicked: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.primaryDark))
            .padding(vertical = 15.dp)
    ) {
        Icon(
            icon,
            contentDescription = "Close the dialog",
            tint = Color.White,
            modifier = Modifier
                .weight(
                    1f
                )
                .clickable { onIconClicked?.apply { invoke() } }.run {
                    iconSize?.let { size(it) } ?: this
                }
        )
        Spacer(modifier = Modifier.weight(4f))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White, modifier = Modifier.weight(4f)
        )
        Spacer(modifier = Modifier.weight(4f))
    }
}