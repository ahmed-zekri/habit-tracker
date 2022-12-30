package com.example.habittracker.data.model


sealed class TaskDays {
    class SpecificDaysTarget(val days: Set<Int>? = null) : TaskDays()
    class WeeklyDaysTarget(val number: DaysPerWeek? = null) : TaskDays()
    class MonthlyDaysTarget(val number: DaysPerMonth? = null) : TaskDays()
    class SpacedDaysTarget(val number: DaysInterval? = null) : TaskDays()

}

@JvmInline
value class DaysPerWeek(val daysPerWeek: Int) {
    init {
        if (daysPerWeek > 7) throw IllegalStateException("Number of days per week must be less than 8")
    }
}

@JvmInline
value class DaysPerMonth( val daysPerMonth: Int) {
    init {
        if (daysPerMonth > 31) throw IllegalStateException("Number of days per month must be less than 32")
    }
}

@JvmInline
value class DaysInterval( val daysInterval: Int) {
    init {
        if (daysInterval > 15) throw IllegalStateException("Days interval value must be less than 16")
    }
}
