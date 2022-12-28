package com.example.habittracker.ui.habitCreationScreen

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
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HabitCreationViewModel @Inject constructor(
    private val addHabitUseCase: AddHabitUseCase,
    private val getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HabitsListState())
    val state: State<HabitsListState> = _state
    private var habit: Habit? = null


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
                        withContext(Dispatchers.IO) {

                        }
                    else -> {}
                }
            }
        }
    }

    fun updateOrGetHabit(
        habitName: String? = null,
        streak: Int? = null,
        goal: Int? = null,
        best: Int? = null,
        icon: String? = null,
        date: Date? = null
    ): Habit? {
        habitName?.apply {
            habit?.let {
                it.name = this
                return it
            }
            habit = Habit(name = this)
        }
        streak?.apply {
            habit?.let {
                it.streak = this
                return it
            }
            habit = Habit(streak = this)
        }
        goal?.apply {
            habit?.let {
                it.goal = this
                return it
            }
            habit = Habit(goal = this)
        }
        best?.apply {
            habit?.let {
                it.best = this
                return it
            }
            habit = Habit(goal = this)
        }
        icon?.apply {
            habit?.let {
                it.image = this
                return it
            }
            habit = Habit(image = this)
        }
        date?.apply {
            habit?.let {
                it.date = this
                return it
            }
            habit = Habit(date = this)
        }
        return habit
    }

    fun getHabits() {
        viewModelScope.launch(Dispatchers.Main) {
            getAllHabitsUseCase().collect {
                when (it) {
                    is ResultHolder.Success<*> ->
                        _state.value = HabitsListState(habits = it.data ?: listOf())
                    is ResultHolder.Error<*> ->
                        _state.value = HabitsListState(error = it.error!!)
                }
            }
        }
    }
}
