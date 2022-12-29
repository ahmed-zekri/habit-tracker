package com.example.habittracker.data.model


sealed class TaskDays {
    class SpecificDaysTarget(var days: Set<Int>? = null) : TaskDays()
    class WeeklyDaysTarget(var number: DaysPerWeek? = null) : TaskDays()
    class MonthlyDaysTarget(var number: DaysPerMonth? = null) : TaskDays()
    class SpacedDaysTarget(var number: DaysInterval? = null) : TaskDays()

}

@JvmInline
value class DaysPerWeek(private val daysPerWeek: Int) {
    init {
        if (daysPerWeek > 7) throw IllegalStateException("Number of days per week must be less than 8")
    }
}

@JvmInline
value class DaysPerMonth(private val daysPerMonth: Int) {
    init {
        if (daysPerMonth > 7) throw IllegalStateException("Number of days per month must be less than 31")
    }
}

@JvmInline
value class DaysInterval(private val daysInterval: Int) {
    init {
        if (daysInterval > 15) throw IllegalStateException("Days interval value must be less than 16")
    }
}
