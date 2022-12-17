package com.example.habittracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.ui.mainScreen.HabitCreationViewModel
import com.example.habittracker.ui.mainScreen.HabitScreen
import com.example.habittracker.ui.mainScreen.HabitViewModel
import com.example.habittracker.ui.mainScreen.HabitsCreationScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.Home.path,
    navHostController: NavHostController = rememberNavController(),
    habitCreationViewModel: HabitCreationViewModel,
    habitViewModel: HabitViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(startDestination) {
            HabitsCreationScreen(habitCreationViewModel, navHostController)
        }
        composable(Destinations.Habit.path) {
            HabitScreen(habitViewModel, navHostController)
        }
    }
}