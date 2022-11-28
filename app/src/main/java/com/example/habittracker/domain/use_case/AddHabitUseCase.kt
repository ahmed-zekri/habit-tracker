package com.example.habittracker.domain.use_case

import com.example.habittracker.data.model.Habit
import com.example.habittracker.domain.repositories.HabitsRepository

class AddHabitUseCase constructor(private val habitsRepository: HabitsRepository) {
    suspend operator fun invoke(habit: Habit) = habitsRepository.addHabit(habit)
}