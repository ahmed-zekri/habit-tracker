package com.example.habittracker.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "habit")
@Parcelize
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String,
    val streak: Int,
    val best: Int,
    val goal: Int,
    val image: String,
    val date: Date
) : Parcelable