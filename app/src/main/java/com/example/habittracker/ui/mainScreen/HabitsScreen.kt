package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HabitsScreen(mainScreenViewModel: HabitViewModel = viewModel()) {
    val state by mainScreenViewModel.state
    if (state.error.isNotEmpty())
        Text(text = state.error)
    else
        LazyVerticalGrid(columns = GridCells.Adaptive(160.dp), content = {
            items(state.habits.size) { index ->
                Card {
                    Text(text = state.habits[index].name)
                }
            }
        })
}