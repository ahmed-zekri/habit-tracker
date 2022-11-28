package com.example.habittracker.ui.mainScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.ResultHolder
import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.use_case.AddHabitUseCase
import com.example.habittracker.domain.use_case.GetAllHabitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    val addHabitUseCase: AddHabitUseCase, val getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HabitState())
    val state: State<HabitState> = _state

    init {
        getHabits()
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            addHabitUseCase(
                habit
            ).collect {
                when (it) {
                    is ResultHolder.Error<*> ->
                        launch(Dispatchers.Main) {
                            _state.value = HabitState(error = it.error!!)
                        }
                    else -> {}
                }
            }
        }
    }

    fun getHabits() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllHabitsUseCase().collect {
                when (it) {
                    is ResultHolder.Success<*> ->
                        launch(Dispatchers.Main) {
                            _state.value = HabitState(habits = it.data as List<Habit>)
                        }
                    is ResultHolder.Error<*> ->
                        launch(Dispatchers.Main) {
                            _state.value = HabitState(error = it.error!!)
                        }
                }
            }
        }
    }
}
