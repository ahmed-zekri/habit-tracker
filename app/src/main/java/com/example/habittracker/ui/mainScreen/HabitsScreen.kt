package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.data.constants.ICONS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(mainScreenViewModel: HabitViewModel = viewModel()) {
    val state by mainScreenViewModel.state
    val openDialog = remember { mutableStateOf(false) }
    val openImageDialog = remember { mutableStateOf(false) }

    LazyVerticalGrid(modifier = Modifier
        .background(color = Color(255, 112, 76))
        .fillMaxSize(), columns = GridCells.Adaptive(160.dp), content = {
        items(state.habits.size + 1) { index ->
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(5.dp)
            ) {
                Canvas(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (index < state.habits.size) showHabit() else openDialog.value = true
                    }) {
                    drawCircle(color = Color.White, style = Stroke(25f), radius = 150f)
                }

                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(70.dp),
                    imageVector = if (index < state.habits.size) Icons.Default.Accessibility else Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    })
    if (openDialog.value)
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                }) { Text(text = "Add habit") }
            },
            title = { Text(text = "Adding Habit") },
            text = {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(text = "Enter habit name") })
                Button(onClick = {openImageDialog.value=true}) {
                    
                }
            })
    if (openImageDialog.value)
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                }) { Text(text = "Choose an image") }
            },
            title = { Text(text = "Choosing image") },
            text = {
                LazyVerticalGrid(columns = GridCells.Adaptive(80.dp)) {
                    items(ICONS.size){
                        index->
                        Icon(imageVector = ICONS[index], contentDescription =null )

                    }
                }
            })
}

fun showHabit() {
    TODO("Not yet implemented")
}
