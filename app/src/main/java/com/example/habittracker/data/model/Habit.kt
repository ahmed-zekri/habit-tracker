package com.example.habittracker.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Entity(tableName = "habit")
@Parcelize
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var streak: Int = 0,
    var best: Int = 0,
    var goal: Int = 0,
    var icon: String? = null,
    var date: Date? = null,
    var periodMeasurement: PeriodMeasurement? = null,
    var taskDays: @RawValue TaskDays? = null
) : Parcelable

enum class PeriodMeasurement {
    DAILY, WEEKLY
}