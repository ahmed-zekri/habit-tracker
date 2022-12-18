package com.example.habittracker.data.utils

fun Int.extractDatePart(dataPart: DatePart): String = when (dataPart) {
    DatePart.Month -> when (this) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> throw IllegalArgumentException("Invalid month value")
    }
    DatePart.Day -> when (this) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> throw IllegalArgumentException("Invalid Day value")
    }
}

enum class DatePart {
    Day, Month
}
