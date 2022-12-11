package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.data.constants.ICONS

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
                    .size(185.dp)
                    .padding(5.dp)
            ) {
                if (index < habits.size)
                    Text(
                        text = habits[index].name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 150.dp)
                    )
                Box(Modifier.size(500.dp)) {
                    Canvas(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (index < habits.size) showHabit() else openDialog.value = true
                        }) {
                        drawCircle(color = Color.White, style = Stroke(25f), radius = 150f)
                    }
                    Icon(
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        imageVector = if (index < habits.size) ICONS.find { it.name == habits[index].image }
                            ?: Icons.Default.NoTransfer else Icons.Default.Add,
                        contentDescription = null
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

fun showHabit() {
    TODO("Not yet implemented")
}
