package com.example.habittracker.ui.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.habittracker.data.utils.horizontalAnimation
import com.example.habittracker.data.utils.verticalAnimation
import com.example.habittracker.ui.mainScreen.AllHabitsScreen
import com.example.habittracker.ui.habitCreationScreen.*
import com.example.habittracker.ui.habitViewScreen.HabitViewModel
import com.example.habittracker.ui.habitViewScreen.habitScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.Home.path,
    navHostController: NavHostController = rememberAnimatedNavController(),
    allHabitsViewModel: AllHabitsViewModel,
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
                verticalAnimation().first
            },
            exitTransition = {
                verticalAnimation().second
            }) {
            AllHabitsScreen(allHabitsViewModel, navHostController)
        }
        composable(Destinations.HabitCreation.path) {
            HabitCreationScreen(navHostController, allHabitsViewModel)
        }
        composable(
            Destinations.HabitCreationConfirmation.path,

            exitTransition = {
                horizontalAnimation().second
            }) {
            HabitConfirmScreen(navHostController, allHabitsViewModel)
        }
        composable(Destinations.Habit.path) {
            habitScreen(habitViewModel, navHostController)
        }

        composable(
            startDestination,
            enterTransition = {
                verticalAnimation().first
            },
            exitTransition = {
                verticalAnimation().second
            }) {
            AllHabitsScreen(allHabitsViewModel, navHostController)
        }
        composable(Destinations.HabitIconSelection.path, enterTransition = {
            verticalAnimation().first
        },
            exitTransition = {
                verticalAnimation().second
            }) {
            HabitIconSelection(allHabitsViewModel, navHostController)
        }

        composable(Destinations.HabitDurationMeasurement.path, enterTransition = {
            verticalAnimation().first
        },
            exitTransition = {
                verticalAnimation().second
            }) {
            HabitDurationMeasurementScreen(navHostController, allHabitsViewModel)
        }

        composable(Destinations.HabitTaskDays.path, enterTransition = {
            verticalAnimation().first
        },
            exitTransition = {
                verticalAnimation().second
            }) {
            TaskDaysScreen(navHostController, allHabitsViewModel)
        }
    }
}

