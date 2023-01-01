package com.example.habittracker.ui.habitCreationScreen

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.ResultHolder
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.model.PeriodMeasurement
import com.example.habittracker.data.model.TaskDays
import com.example.habittracker.domain.use_case.AddHabitUseCase
import com.example.habittracker.domain.use_case.GetAllHabitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AllHabitsViewModel @Inject constructor(
    private val addHabitUseCase: AddHabitUseCase,
    private val getAllHabitsUseCase: GetAllHabitsUseCase, application: Application
) : AndroidViewModel(application) {
    private val _state = mutableStateOf(HabitsListState())
    val state: State<HabitsListState> = _state
    private var habit: Habit? = null

    fun addHabit() = updateOrGetHabit()?.let { habit ->
        viewModelScope.launch(Dispatchers.IO) {
            addHabitUseCase(
                habit
            ).collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ResultHolder.Error<*> -> {
                            Toast.makeText(getApplication(), it.error, Toast.LENGTH_SHORT).show()

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
        date: Date? = null,
        periodMeasurement: PeriodMeasurement? = null,
        taskDays: TaskDays? = null
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
        periodMeasurement?.let {
            habit = habit?.run {
                this.periodMeasurement = it
                this

            } ?: Habit(periodMeasurement = it)

        }
        taskDays?.let {
            habit = habit?.run {
                this.taskDays = it
                this

            } ?: Habit(taskDays = it)

        }
        return habit
    }

    fun getHabits() {
        viewModelScope.launch(Dispatchers.Main) {
            getAllHabitsUseCase().collect {
                when (it) {
                    is ResultHolder.Success<*> ->
                        _state.value = HabitsListState(habits = it.data ?: listOf())
                    is ResultHolder.Error<*> -> {
                        _state.value = HabitsListState(error = it.error!!)
                        Toast.makeText(getApplication(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun getDayString(day: Int, charMode: Boolean = true) = when (day) {

        0 -> if (charMode) "S" else "Sunday"
        1 -> if (charMode) "M" else "Monday"
        2 -> if (charMode) "T" else "Tuesday"
        3 -> if (charMode) "W" else "Wednesday"
        4 -> if (charMode) "T" else "Thursday"
        5 -> if (charMode) "F" else "Friday"
        6 -> if (charMode) "S" else "Saturday"
        else -> throw IllegalStateException("Day value must be less than 7")

    }

    fun frequencyState(): String = when (val taskDays = habit?.taskDays) {
        is TaskDays.SpecificDaysTarget -> if (taskDays.days?.size == 7) "Every day"
        else "Specific days"
        is TaskDays.SpacedDaysTarget -> "Every ${taskDays.number?.daysInterval} days"
        is TaskDays.WeeklyDaysTarget -> "${taskDays.number?.daysPerWeek} days per week"
        is TaskDays.MonthlyDaysTarget -> "${taskDays.number?.daysPerMonth} days per month"

        else -> ""
    }


}
