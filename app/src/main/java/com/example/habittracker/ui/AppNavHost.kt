package com.example.habittracker.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.habittracker.ui.mainScreen.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.Home.path,
    navHostController: NavHostController = rememberAnimatedNavController(),
    habitCreationViewModel: HabitCreationViewModel,
    habitViewModel: HabitViewModel
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(
            startDestination,
            enterTransition = { slideInVertically(animationSpec = tween(500)) },
            exitTransition = {slideOutVertically(animationSpec = tween(500)) }) {
            HabitsCreationScreen(habitCreationViewModel, navHostController)
        }
        composable(Destinations.HabitCreation.path) {
            HabitAlertDialog(navHostController)
        }
        composable(Destinations.Habit.path) {
            habitScreen(habitViewModel, navHostController)
        }
    }
}