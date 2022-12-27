package com.example.habittracker.ui

sealed class Destinations(val path: String) {
    object HabitCreation : Destinations("HABIT_CREATION_PATH")
    object Home : Destinations("HOME_PATH")
    object Habit : Destinations("HABIT")
    object HabitCreationConfirmation :
        Destinations("Habit_CREATION_CONFIRMATION")
}