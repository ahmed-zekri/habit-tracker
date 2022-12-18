package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.data.constants.ICONS
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.utils.getParcelableData
import java.util.*

@Composable
fun habitScreen(
    habitViewModel: HabitViewModel = viewModel(), navHostController: NavHostController
) =
    remember {
        mutableStateOf(
            navHostController.currentBackStackEntry?.arguments?.getParcelableData(
                "habit",
                Habit::class.java
            )
        )
    }.apply {
        value?.let { habit ->
            habitViewModel.setHabit(habit)
            Column(Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally) {
                val calendar = Calendar.getInstance().apply { time = habit.date }
                Text(
                    text = habitViewModel.extractDateString(calendar),
                    modifier = Modifier
                        .padding(30.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                ICONS.find { it.name == habit.image }?.let {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                color = Color(red = 245, green = 23, blue = 79),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Center)
                                .size(65.dp), tint = Color.White
                        )
                    }
                }
            }
        }
    }
