package com.example.habittracker.domain.use_case

import com.example.habittracker.data.ResultHolder
import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.repositories.HabitsRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.flow

class AddHabitUseCase (private val habitsRepository: HabitsRepository) {
    operator fun invoke(habit: Habit) = flow {
        try {
            if (habit.name == null)
                currentCoroutineContext().cancel(null)
            else
                if (habitsRepository.getHabitsByName(habit.name!!).isNotEmpty())
                    emit(ResultHolder.Error(error = "Habit with the same name already exists"))
                else {
                    habitsRepository.addHabit(habit)
                    emit(ResultHolder.Success(data = habit))
                }
        } catch (e: Exception) {
            emit(ResultHolder.Error(error = e.message ?: ""))
        }
    }
}