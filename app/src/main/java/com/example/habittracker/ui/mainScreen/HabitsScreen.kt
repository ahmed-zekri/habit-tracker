package com.example.habittracker.ui.mainScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HabitsScreen(mainScreenViewModel: HabitViewModel = viewModel()) {
    val state by mainScreenViewModel.state
    Text(text = state.habits.getOrNull(0)?.name?:"")
}