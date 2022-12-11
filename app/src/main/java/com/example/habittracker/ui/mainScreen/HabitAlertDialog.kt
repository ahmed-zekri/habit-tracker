package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habittracker.data.constants.ICONS
import com.example.habittracker.data.model.Habit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitAlertDialog(onConfirm: Habit.() -> Unit) {
    val openImageDialog = remember { mutableStateOf(false) }
    val iconName = remember {
        mutableStateOf<String?>(null)
    }
    val habitText = remember {
        mutableStateOf<String?>(null)
    }

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(
                enabled = habitText.value?.isNotEmpty() == true && iconName.value?.isNotEmpty() == true,
                onClick = {
                    iconName.value?.let { iconName ->
                        habitText.value?.let { habitText ->
                            onConfirm(
                                Habit(
                                    name = habitText,
                                    goal = 1,
                                    best = 1,
                                    streak = 1,
                                    image = iconName
                                )
                            )
                        }
                    }
                }) { Text(text = "Add habit") }
        },
        title = { Text(text = "Adding habit") },
        text = {
            Column {
                OutlinedTextField(
                    value = habitText.value ?: "", label = { Text(text = "Habit name")},
                    onValueChange = { habitText.value = it },
                    placeholder = { Text(text = "Enter habit name") },
                    trailingIcon = {
                        iconName.value?.let { iconName ->
                            ICONS.find { it.name == iconName }?.let { icon ->
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        }
                    })
                Button(modifier = Modifier.padding(top = 15.dp), onClick = {
                    openImageDialog.value = true
                }) {
                    Text(text = "Open image picker")
                }
            }
        })
    if (openImageDialog.value) {
        HabitAlertImageDialog(iconName.value,{
            openImageDialog.value = false
            iconName.value = this
        }) {
            openImageDialog.value = false
        }
    }
}