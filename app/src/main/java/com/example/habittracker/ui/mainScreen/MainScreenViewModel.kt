package com.example.habittracker.ui.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.use_case.AddHabitUseCase
import com.example.habittracker.domain.use_case.GetAllHabitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    addHabitUseCase: AddHabitUseCase,
    getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {
    val state = mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            addHabitUseCase(Habit(name = "habit1", best = 25, streak = 3))
            state.value = getAllHabitsUseCase()[0].name
        }
    }
}