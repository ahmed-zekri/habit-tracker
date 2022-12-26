package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogTopBar(icon: ImageVector, onIconClicked: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(red = 190, green = 60, blue = 60))
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
                .clickable { onIconClicked?.apply { invoke() } }
        )
        Spacer(modifier = Modifier.weight(4f))
        Text(
            text = "Add Task",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White, modifier = Modifier.weight(3f)
        )
        Spacer(modifier = Modifier.weight(4f))
    }
}