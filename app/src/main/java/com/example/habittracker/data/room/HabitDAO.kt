package com.example.habittracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.habittracker.data.model.Habit

@Dao
interface HabitDAO {
    @Query("select * from HABIT")
    suspend fun getAllHabits(): List<Habit>
    @Query("select * from HABIT where name=:name ")
    suspend fun getHabitByName(name:String): List<Habit>
    @Insert
    suspend fun addHabits(habit: Habit)
    @Delete
    suspend fun removeHabits(habit: Habit)
}