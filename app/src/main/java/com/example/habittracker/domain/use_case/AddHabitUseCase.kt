package com.example.habittracker.domain.use_case

import com.example.habittracker.data.ResultHolder
import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.repositories.HabitsRepository
import kotlinx.coroutines.flow.flow

class AddHabitUseCase constructor(private val habitsRepository: HabitsRepository) {
    operator fun invoke(habit: Habit) = flow {
        if (habitsRepository.getHabitsByName(habit.name).isNotEmpty())
            emit(ResultHolder.Error(error = "Habit with the same name already exists"))
        else {
            habitsRepository.addHabit(habit)
            emit(ResultHolder.Success(data = habit))
        }
    }
}