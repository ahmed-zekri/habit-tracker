package com.example.habittracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habittracker.data.model.Habit

@Database(
    entities = [Habit::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun getHabitDao(): HabitDAO
}