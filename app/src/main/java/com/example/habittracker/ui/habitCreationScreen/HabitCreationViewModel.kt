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

    fun addHabit() = updateOrGetHabit()?.let { habit ->
        viewModelScope.launch(Dispatchers.IO) {
            addHabitUseCase(
                habit
            ).collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultHolder.Error<*> -> {

                        }
                        else -> {}
                    }
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
        habitName?.let {
            habit = habit?.run {
                this.name = it
                this

            } ?: Habit(name = it)

        }
        streak?.let {
            habit = habit?.run {
                this.streak = it
                this

            } ?: Habit(streak = it)

        }
        goal?.let {
            habit = habit?.run {
                this.goal = it
                this

            } ?: Habit(goal = it)

        }
        icon?.let {
            habit = habit?.run {
                this.icon = it
                this

            } ?: Habit(icon = it)

        }

        date?.let {
            habit = habit?.run {
                this.date = it
                this

            } ?: Habit(date = it)

        }

        best?.let {
            habit = habit?.run {
                this.best = it
                this

            } ?: Habit(best = it)

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
