package com.example.habittracker.ui.navigation

sealed class Destinations(val path: String) {
    object HabitCreation : Destinations("HABIT_CREATION_PATH")
    object Home : Destinations("HOME_PATH")
    object Habit : Destinations("HABIT")
    object HabitCreationConfirmation :
        Destinations("Habit_CREATION_CONFIRMATION")

    object HabitIconSelection : Destinations("HABIT_ICON_SELECTION")
    object HabitDurationMeasurement  : Destinations("HABIT_DURATION_MEASUREMENT")
}