package com.example.habittracker.data.room

import androidx.room.TypeConverter
import com.example.habittracker.data.model.TaskDays
import com.google.gson.Gson
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun taskDaysToString(taskDays: TaskDays?): String? =
        taskDays?.let { Gson().toJson(taskDays) }

    @TypeConverter
    fun taskDaysFromString(taskDays: String?): TaskDays? =
        taskDays?.let { Gson().fromJson(it, TaskDays::class.java) }
}