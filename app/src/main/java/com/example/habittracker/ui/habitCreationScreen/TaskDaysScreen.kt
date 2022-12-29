package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.R
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
        mutableStateOf(setOf(0, 1, 2, 3, 4, 5, 6))
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
                        vertical = if (selectionState.value is TaskDays) 15.dp else 25.dp
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
                    text = "Specific days of the week",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.align(CenterVertically)
                )
                if (selectionState.value is TaskDays.SpecificDaysTarget)
                    Icon(
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
                    val backgroundColor = remember {
                        mutableStateOf(Color.White)
                    }
                    val textColor = remember {
                        mutableStateOf(Color.Black)
                    }
                    Box(modifier = Modifier
                        .size(40.dp)
                        .background(backgroundColor.value, CircleShape)
                        .padding(5.dp)
                        .clickable {

                            selectedSpecifiedDays.value = buildSet {
                                addAll(selectedSpecifiedDays.value)
                                if (day in selectedSpecifiedDays.value) {
                                    backgroundColor.value = Color.Transparent
                                    textColor.value = Color.White
                                    remove(day)
                                } else {
                                    add(day)
                                    backgroundColor.value = Color.White
                                    textColor.value = Color.Black
                                }
                            }
                            selectionState.value =
                                TaskDays.SpecificDaysTarget(selectedSpecifiedDays.value)
                        }) {

                        Text(
                            text = habitCreationViewModel.getDayString(day),
                            color = textColor.value,
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
        }


    }

}