package com.example.habittracker.ui.mainScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.use_case.AddHabitUseCase
import com.example.habittracker.domain.use_case.GetAllHabitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    addHabitUseCase: AddHabitUseCase, getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HabitState())
    val state: State<HabitState> = _state

    init {
        viewModelScope.launch {
      val habits= async(Dispatchers.IO){     addHabitUseCase(Habit(name = "habit1", best = 25, streak = 3, goal = 55))
       HabitState(getAllHabitsUseCase()) }
            _state.value=habits.await()

        }
    }
}