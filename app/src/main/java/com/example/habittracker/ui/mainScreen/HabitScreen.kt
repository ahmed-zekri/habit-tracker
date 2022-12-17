package com.example.habittracker.ui.mainScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.utils.getParcelableData

@Composable
fun HabitScreen(
    mainScreenViewModel: HabitViewModel = viewModel(), navHostController: NavHostController
) {
    val habit = remember {
        mutableStateOf<Habit?>(null)
    }
    habit.value =
        navHostController.currentBackStackEntry?.arguments?.getParcelableData(
            "habit",
            Habit::class.java
        )

    Text(text = habit.value?.name ?: "")
}
