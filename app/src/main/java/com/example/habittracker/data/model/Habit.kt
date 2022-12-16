package com.example.habittracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String,
    val streak: Int,
    val best: Int,
    val goal: Int,
    val image: String,
    val date: Date
)