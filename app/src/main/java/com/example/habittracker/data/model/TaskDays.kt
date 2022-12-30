package com.example.habittracker.data.model


sealed class TaskDays {
    class SpecificDaysTarget(days: Set<Int>? = null) : TaskDays()
    class WeeklyDaysTarget(number: DaysPerWeek? = null) : TaskDays()
    class MonthlyDaysTarget(number: DaysPerMonth? = null) : TaskDays()
    class SpacedDaysTarget(number: DaysInterval? = null) : TaskDays()

}

@JvmInline
value class DaysPerWeek(val daysPerWeek: Int) {
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
