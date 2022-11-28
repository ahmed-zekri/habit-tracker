package com.example.habittracker.domain.repositories

import com.example.habittracker.data.model.Habit

interface HabitsRepository {
    suspend fun getAllHabits(): List<Habit>
    suspend fun getHabitsByName(name: String): List<Habit>
    suspend fun addHabit(habit: Habit)
    suspend fun removeHabit(habit: Habit)
    fun editHabit(habit: Habit)
}