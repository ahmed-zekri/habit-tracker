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
import androidx.compose.material3.Icon
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

@Composable
fun HabitsScreen(mainScreenViewModel: HabitViewModel = viewModel()) {
    val state by mainScreenViewModel.state
    val openDialog = remember { mutableStateOf(false) }

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
        HabitAlertDialog {

        }

}

fun showHabit() {
    TODO("Not yet implemented")
}
