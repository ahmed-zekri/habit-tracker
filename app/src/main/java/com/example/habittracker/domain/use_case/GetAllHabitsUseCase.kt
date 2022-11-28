package com.example.habittracker.domain.use_case

import com.example.habittracker.data.ResultHolder
import com.example.habittracker.domain.repositories.HabitsRepository
import kotlinx.coroutines.flow.flow

class GetAllHabitsUseCase(private val habitsRepository: HabitsRepository) {
    operator fun invoke() = flow {
        try {
            emit(ResultHolder.Success(data = habitsRepository.getAllHabits()))
        } catch (e: Exception) {
            emit(ResultHolder.Error(error = e.message ?: "Unknown error"))
        }
    }
}