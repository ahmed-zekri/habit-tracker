package com.example.habittracker.ui

sealed class Destinations(val path:String) {
    object Home:Destinations("HOME_PATH")
    object Habit:Destinations("HABIT")
}