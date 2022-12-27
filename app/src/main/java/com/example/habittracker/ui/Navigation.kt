package com.example.habittracker.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.habittracker.data.constants.ALERT_DIALOG_ANIMATION_DURATION_MILLIS
import com.example.habittracker.ui.mainScreen.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
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
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(
                        ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
                    )
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(
                        ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
                    )
                )
            }) {
            HabitsCreationScreen(habitCreationViewModel, navHostController)
        }
        composable(Destinations.HabitCreation.path) {
            HabitCreationScreen(navHostController)
        }
        composable(Destinations.HabitCreationConfirmation.path,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(
                        ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(
                        ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
                    )
                )
            }) {
            HabitConfirmScreen(navHostController)
        }
        composable(Destinations.Habit.path) {
            habitScreen(habitViewModel, navHostController)
        }
    }
}