package com.example.habittracker.ui.habitCreationScreen

import com.example.habittracker.data.model.Habit

data class HabitsListState(val habits: List<Habit> = listOf(), val error: String = "")
