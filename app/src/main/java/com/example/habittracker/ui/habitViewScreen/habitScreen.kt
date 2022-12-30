package com.example.habittracker.ui.habitViewScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color(red = 147, green = 194, blue = 67)),
                horizontalAlignment = CenterHorizontally
            ) {
                val calendar = habit.date?.run { Calendar.getInstance().apply { time = this@run } }
                    ?: Calendar.getInstance()
                Text(
                    fontFamily = FontFamily.Serif,
                    text = habitViewModel.extractDateString(calendar),
                    modifier = Modifier
                        .padding(30.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )

                Text(
                    fontFamily = FontFamily.Serif, text = habit.name ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White, modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
