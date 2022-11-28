package com.example.habittracker.data.repositories

import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.room.HabitDAO
import com.example.habittracker.domain.repositories.HabitsRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(private val habitDAO: HabitDAO) : HabitsRepository {
    override suspend fun getAllHabits(): List<Habit> = habitDAO.getAllHabits()
    override suspend fun getHabitsByName(name: String): List<Habit> = habitDAO.getHabitByName(name)
    override suspend fun addHabit(habit: Habit) = habitDAO.addHabits(habit)
    override suspend fun removeHabit(habit: Habit) = habitDAO.removeHabits(habit)
    override fun editHabit(habit: Habit) {
        TODO("Not yet implemented")
    }
}