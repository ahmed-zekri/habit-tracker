package com.example.habittracker.ui.mainScreen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.utils.navigateToRoute
import com.example.habittracker.R
import com.example.habittracker.ui.habitCreationScreen.AllHabitsViewModel
import com.example.habittracker.ui.navigation.Destinations
import compose.icons.TablerIcons
import compose.icons.tablericons.Plus

@Composable
fun AllHabitsScreen(
    allHabitsViewModel: AllHabitsViewModel = viewModel(),
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = false) {


    }
    val habits = allHabitsViewModel.state.value.habits
    LaunchedEffect(key1 = false) {
        allHabitsViewModel.getHabits()

    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(modifier = Modifier
            .background(color = colorResource(id = R.color.primary))
            .fillMaxSize(), columns = GridCells.Fixed(2), content = {
            items(habits.size + 1) { index ->
                Box(
                    modifier = Modifier
                        .size(210.dp)
                        .clickable {
                            if (index == habits.size)
                                navHostController.navigateToRoute(Destinations.HabitCreation.path)
                            else
                                showHabit(habits[index], navHostController)
                        }
                ) {
                    Column {
                        Box(Modifier.padding(start = 25.dp, top = 20.dp)) {
                            DoubleColorCircularProgressBar(
                                progress = if (index == habits.size) 1f else habits[index].streak.toFloat() / habits[index].goal.let { if (it == 0) 1 else 0 },
                                stroke = 7.dp,
                                colorProgress = Color.White,
                                colorRemaining = Color.Black
                            )
                            Column(
                                Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 3.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(70.dp),
                                    imageVector = TablerIcons.Plus,
                                    contentDescription = null, tint = Color.White
                                )
                                if (index < habits.size)
                                    Text(
                                        fontFamily = FontFamily.Serif,
                                        text = habits[index].streak.toString(),
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        modifier = Modifier.padding(top = 5.dp)
                                    )
                            }
                        }
                        if (index < habits.size)
                            Text(
                                fontFamily = FontFamily.Serif,
                                text = habits[index].name ?: "",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(start = 20.dp, top = 10.dp)
                            )
                    }
                }
            }
        })
    }
}

fun showHabit(habit: Habit, navHostController: NavHostController) {
    navHostController.apply {
        navigateToRoute(route = Destinations.Habit.path, args = Bundle().apply {
            putParcelable("habit", habit)
        })
    }
}
