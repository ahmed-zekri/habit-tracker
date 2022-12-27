package com.example.habittracker.ui.habitCreationScreen

import com.example.habittracker.data.model.Habit

data class HabitState(val habits: List<Habit> = listOf(), val error: String = "")
