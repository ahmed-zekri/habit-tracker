package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.model.DaysInterval
import com.example.habittracker.data.model.DaysPerMonth
import com.example.habittracker.data.model.DaysPerWeek
import com.example.habittracker.data.model.TaskDays
import com.example.habittracker.data.utils.navigateToRoute
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.screenUtils.ScreenTopBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.CheckCircle

@Composable
fun TaskDaysScreen(
    navHostController: NavHostController? = null, habitCreationViewModel: HabitCreationViewModel
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier.background(color = primaryColor)
    }
    val selectionState = remember {
        mutableStateOf(habitCreationViewModel.updateOrGetHabit()?.taskDays)
    }
    val selectedSpecifiedDays = remember {
        mutableStateOf(
            habitCreationViewModel.updateOrGetHabit()?.taskDays?.let {
                (it as? TaskDays.SpecificDaysTarget)?.days ?: setOf(0, 1, 2, 3, 4, 5, 6)
            } ?: setOf(0, 1, 2, 3, 4, 5, 6)
        )
    }
    val selectedWeeklyDays = remember {
        mutableStateOf(
            habitCreationViewModel.updateOrGetHabit()?.taskDays?.let {
                (it as? TaskDays.WeeklyDaysTarget)?.number ?: DaysPerWeek(1)
            } ?: DaysPerWeek(1)
        )
    }
    val selectedMonthlyDays = remember {
        mutableStateOf(
            habitCreationViewModel.updateOrGetHabit()?.taskDays?.let {
                (it as? TaskDays.MonthlyDaysTarget)?.number ?: DaysPerMonth(7)
            } ?: DaysPerMonth(7)
        )
    }

    val selectedSpacedDays = remember {
        mutableStateOf(
            habitCreationViewModel.updateOrGetHabit()?.taskDays?.let {
                (it as? TaskDays.SpacedDaysTarget)?.number ?: DaysInterval(2)
            } ?: DaysInterval(2)
        )
    }

    Column(modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(8f),
            horizontalAlignment = CenterHorizontally
        ) {
            ScreenTopBar(Icons.Default.ChevronLeft, "Task Days", iconSize = 25.dp) {
                habitCreationViewModel.updateOrGetHabit(taskDays = selectionState.value)
                navHostController?.navigateToRoute(
                    Destinations.HabitCreationConfirmation.path
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = if (selectionState.value is TaskDays.SpecificDaysTarget) 15.dp else 25.dp
                    )
                    .clickable {
                        if (selectionState.value is TaskDays.SpecificDaysTarget) selectionState.value =
                            null
                        else selectionState.value =
                            TaskDays.SpecificDaysTarget(selectedSpecifiedDays.value)

                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {


                Text(
                    fontFamily = FontFamily.Serif, text = "Specific days of the week",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.align(CenterVertically)
                )
                if (selectionState.value is TaskDays.SpecificDaysTarget) Icon(
                    imageVector = FontAwesomeIcons.Regular.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )

            }


            Spacer(modifier = Modifier.height(15.dp))

            if (selectionState.value is TaskDays.SpecificDaysTarget) Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)

            ) {
                (0..6).forEach { day ->

                    Box(modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (day in selectedSpecifiedDays.value) Color.White else Color.Transparent,
                            CircleShape
                        )
                        .padding(5.dp)
                        .clickable {

                            selectedSpecifiedDays.value = buildSet {
                                addAll(selectedSpecifiedDays.value)
                                if (day in selectedSpecifiedDays.value)

                                    remove(day)
                                else
                                    add(day)


                            }
                            selectionState.value =
                                TaskDays.SpecificDaysTarget(selectedSpecifiedDays.value)
                        }) {

                        Text(
                            fontFamily = FontFamily.Serif,
                            text = habitCreationViewModel.getDayString(day),
                            color = if (day in selectedSpecifiedDays.value) Color.Black else Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(
                                Center
                            )
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }

            }




            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = if (selectionState.value is TaskDays.WeeklyDaysTarget) 15.dp else 25.dp
                    )
                    .clickable {
                        if (selectionState.value is TaskDays.WeeklyDaysTarget) selectionState.value =
                            null
                        else selectionState.value =
                            TaskDays.WeeklyDaysTarget(selectedWeeklyDays.value)

                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {


                Text(
                    fontFamily = FontFamily.Serif, text = "Number of days per week",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.align(CenterVertically)
                )
                if (selectionState.value is TaskDays.WeeklyDaysTarget) Icon(
                    imageVector = FontAwesomeIcons.Regular.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )

            }

            Spacer(modifier = Modifier.height(15.dp))
            if (selectionState.value is TaskDays.WeeklyDaysTarget) Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)

            ) {
                (1..7).forEach { day ->
                    Box(modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selectedWeeklyDays.value.daysPerWeek == day) Color.White else Color.Transparent,
                            CircleShape
                        )
                        .padding(5.dp)
                        .clickable {

                            selectedWeeklyDays.value = DaysPerWeek(day)

                            selectionState.value =
                                TaskDays.WeeklyDaysTarget(selectedWeeklyDays.value)
                        }) {

                        Text(
                            fontFamily = FontFamily.Serif, text = day.toString(),
                            color = if (selectedWeeklyDays.value.daysPerWeek == day) Color.Black else Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(
                                Center
                            )
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }

            }





            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = if (selectionState.value is TaskDays.WeeklyDaysTarget) 15.dp else 25.dp
                    )
                    .clickable {
                        if (selectionState.value is TaskDays.MonthlyDaysTarget) selectionState.value =
                            null
                        else selectionState.value =
                            TaskDays.MonthlyDaysTarget(selectedMonthlyDays.value)

                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {


                Text(
                    fontFamily = FontFamily.Serif, text = "Number of days per month",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.align(CenterVertically)
                )
                if (selectionState.value is TaskDays.MonthlyDaysTarget) Icon(
                    imageVector = FontAwesomeIcons.Regular.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )

            }

            Spacer(modifier = Modifier.height(15.dp))
            if (selectionState.value is TaskDays.MonthlyDaysTarget)
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(40.dp),
                    modifier = Modifier
                        .height(200.dp)
                        .padding(15.dp)
                ) {
                    items(31) { daysCount ->
                        Box(modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (selectedMonthlyDays.value.daysPerMonth == daysCount + 1) Color.White else Color.Transparent,
                                CircleShape
                            )

                            .clickable {

                                selectedMonthlyDays.value = DaysPerMonth(daysCount + 1)

                                selectionState.value =
                                    TaskDays.MonthlyDaysTarget(selectedMonthlyDays.value)
                            }) {

                            Text(
                                text = (daysCount + 1).toString(),
                                color = if (selectedMonthlyDays.value.daysPerMonth == daysCount + 1) Color.Black else Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(
                                    Center
                                ),
                                fontFamily = FontFamily.Serif
                            )


                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }




            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .fillMaxWidth()
                    .padding(
                        horizontal = 15.dp,
                        vertical = if (selectionState.value is TaskDays.WeeklyDaysTarget) 15.dp else 25.dp
                    )
                    .clickable {
                        if (selectionState.value is TaskDays.SpacedDaysTarget) selectionState.value =
                            null
                        else selectionState.value =
                            TaskDays.SpacedDaysTarget(selectedSpacedDays.value)

                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {


                Text(
                    fontFamily = FontFamily.Serif,
                    text = "Every ${selectedSpacedDays.value.daysInterval} days",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.align(CenterVertically)
                )
                if (selectionState.value is TaskDays.SpacedDaysTarget) Icon(
                    imageVector = FontAwesomeIcons.Regular.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )

            }

            Spacer(modifier = Modifier.height(15.dp))
            if (selectionState.value is TaskDays.SpacedDaysTarget)
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(40.dp),
                    modifier = Modifier
                        .height(200.dp)
                        .padding(15.dp)
                ) {
                    items(14) { daysCount ->
                        Box(modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (selectedSpacedDays.value.daysInterval == daysCount + 2) Color.White else Color.Transparent,
                                CircleShape
                            )

                            .clickable {

                                selectedSpacedDays.value = DaysInterval(daysCount + 2)

                                selectionState.value =
                                    TaskDays.SpacedDaysTarget(selectedSpacedDays.value)
                            }) {

                            Text(
                                text = (daysCount + 2).toString(),
                                color = if (selectedSpacedDays.value.daysInterval == daysCount + 2) Color.Black else Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(
                                    Center
                                ),
                                fontFamily = FontFamily.Serif
                            )


                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }


        }


    }


}