package com.example.habittracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habittracker.data.model.Habit

@Database(
    entities = [Habit::class],
    version = 1
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun getHabitDao() : HabitDAO
}