package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.habittracker.ui.Navigation
import com.example.habittracker.ui.mainScreen.HabitCreationViewModel
import com.example.habittracker.ui.mainScreen.HabitViewModel
import com.example.habittracker.ui.theme.HabitTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val habitCreationViewModel: HabitCreationViewModel by viewModels()
    private val habitViewModel: HabitViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        habitCreationViewModel = habitCreationViewModel,
                        habitViewModel = habitViewModel
                    )
                }
            }
        }
    }
}
