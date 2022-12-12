package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NoTransfer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.data.constants.ICONS
import com.example.habittracker.data.model.Habit

@Composable
fun HabitsScreen(mainScreenViewModel: HabitViewModel = viewModel()) {
    val habits = mainScreenViewModel.state.value.habits
    val openDialog = remember { mutableStateOf(false) }

    LazyVerticalGrid(modifier = Modifier
        .background(color = Color(255, 112, 76))
        .fillMaxSize(), columns = GridCells.Fixed(2), content = {
        items(habits.size + 1) { index ->
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(start = 25.dp, top = 5.dp)
                    .clickable {
                        if (index == habits.size)
                            openDialog.value = true
                        else
                            showHabit(habits[index])
                    }
            ) {
                Column {
                    Box {
                        DoubleColorCircularProgressBar(
                            progress = if (index == habits.size) 1f else habits[index].streak.toFloat() / habits[index].goal,
                            stroke = 7.dp,
                            colorProgress = Color.White,
                            colorRemaining = Color.Black
                        )
                        Column(
                            Modifier
                                .align(Alignment.Center)
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(70.dp),
                                imageVector = if (index < habits.size) ICONS.find { it.name == habits[index].image }
                                    ?: Icons.Default.NoTransfer else Icons.Default.Add,
                                contentDescription = null, tint = Color.White
                            )
                            if (index < habits.size)
                                Text(
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
                            text = habits[index].name,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                }
            }
        }
    })
    if (openDialog.value)
        HabitAlertDialog {
            openDialog.value = false
            mainScreenViewModel.addHabit(this)
            mainScreenViewModel.getHabits()
        }
}

fun showHabit(habit: Habit) {
}
