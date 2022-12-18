package com.example.habittracker.ui.mainScreen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.habittracker.data.model.Habit
import com.example.habittracker.data.utils.DatePart
import com.example.habittracker.data.utils.extractDatePart
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    var habit: Habit? = null
        private set

    fun setHabit(habit: Habit) {
        this.habit = habit
        Toast.makeText(getApplication(), habit.goal.toString(), Toast.LENGTH_SHORT).show()
    }

    fun extractDateString(calendar: Calendar): String = "Since ${
        calendar.get(Calendar.DAY_OF_WEEK).extractDatePart(DatePart.Day)
    } ${calendar.get(Calendar.MONTH).extractDatePart(DatePart.Month)} ${
        calendar.get(
            Calendar.DAY_OF_MONTH
        )
    },${calendar.get(Calendar.YEAR)}"
}
