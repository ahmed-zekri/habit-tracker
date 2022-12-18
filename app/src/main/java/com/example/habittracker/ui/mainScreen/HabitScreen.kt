package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.utils.getParcelableData
import java.util.*

@Composable
fun HabitScreen(
    habitViewModel: HabitViewModel = viewModel(), navHostController: NavHostController
) {
    val habitState = remember {
        mutableStateOf(
            navHostController.currentBackStackEntry?.arguments?.getParcelableData(
                "habit",
                Habit::class.java
            )
        )
    }.apply { value?.let { habitViewModel.setHabit(it) } }
    habitState.value?.let { habit ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val calendar = Calendar.getInstance().apply { time = habit.date }

            Text(
                text = habitViewModel.extractDateString(calendar),
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}


