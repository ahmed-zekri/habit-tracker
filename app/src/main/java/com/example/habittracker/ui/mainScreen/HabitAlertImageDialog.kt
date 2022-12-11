package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.R
import com.example.habittracker.data.constants.ICONS

@Composable
fun HabitAlertImageDialog(icon: String?, onConfirm: String.() -> Unit, onDismiss: () -> Unit) {
    val iconName: MutableState<String?> = remember { mutableStateOf(icon) }
    AlertDialog(dismissButton = {
        Button(onClick = { onDismiss() }) {
            Text(text = "Close")
        }
    },
        onDismissRequest = { },
        confirmButton = {
            Button(
                enabled = iconName.value?.isNotEmpty() == true,
                onClick = {
                    iconName.value?.let {
                        onConfirm(it)
                    }
                }) { Text(text = "Select") }
        },
        title = { Text(text = "Choosing image", modifier = Modifier.padding(start = 25.dp)) },
        text = {
            Column(Modifier.padding(start = 15.dp)) {
                LazyVerticalGrid(columns = GridCells.Adaptive(80.dp)) {
                    items(ICONS.size) { index ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            Icon(
                                imageVector = ICONS[index],
                                modifier = Modifier
                                    .clickable {
                                        iconName.value =
                                            if (iconName.value != ICONS[index].name) ICONS[index].name else null
                                    }
                                    .background(
                                        if (iconName.value != ICONS[index].name) Color.Transparent else colorResource(
                                            id = R.color.purple_200
                                        )
                                    )
                                    .size(50.dp)
                                    .padding(5.dp),
                                contentDescription = null
                            )
                        }
                    }
                }
                if (iconName.value == null)
                    Text(
                        text = "Please select an icon",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
            }
        })
}