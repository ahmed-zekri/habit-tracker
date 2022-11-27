package com.example.habittracker.domain.use_case

import com.example.habittracker.domain.repositories.HabitsRepository

class GetAllHabitsUseCase(private val habitsRepository: HabitsRepository) {
    suspend operator fun invoke() = habitsRepository.getAllHabits()
}