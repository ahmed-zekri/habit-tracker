package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.constants.MAXIMUM_HABIT_CHARACTERS
import com.example.habittracker.data.utils.navigateToRoute
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.screenUtils.ScreenTopBar
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowForward
import compose.icons.tablericons.ChevronLeft
import compose.icons.tablericons.CircleCheck
import compose.icons.tablericons.Minus
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitCreationScreen(
    navHostController: NavHostController, allHabitsViewModel: AllHabitsViewModel
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier
            .background(primaryColor)
    }
    val habitText = remember {
        mutableStateOf(allHabitsViewModel.updateOrGetHabit()?.name)
    }

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ScreenTopBar(
            TablerIcons.ChevronLeft,
            "Add task"
        ) { navHostController.navigateToRoute(Destinations.Home.path) }

        Icon(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .size(55.dp),
            imageVector = TablerIcons.CircleCheck,
            contentDescription = "Customized habit",
            tint = Color.White
        )

        Text(
            fontFamily = FontFamily.Serif,
            text = "Tasks start each day as incomplete , Mark a task as done to increase your streak",
            color = colorResource(id = R.color.darkGray),
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            fontFamily = FontFamily.Serif, text = "CREATE YOUR OWN:",
            color = colorResource(id = R.color.darkGray),
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start), fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(197, 62, 63),
                textColor = Color.White
            ),
            value = habitText.value ?: "",
            onValueChange = {
                if (it.length <= MAXIMUM_HABIT_CHARACTERS)
                    habitText.value = it
            },
            placeholder = {
                Text(
                    fontFamily = FontFamily.Serif, text = "Enter task title...",
                    color = colorResource(id = R.color.darkGray)
                )
            },
            trailingIcon = {
                habitText.value?.apply {
                    if (isNotEmpty())
                        Row(Modifier.height(IntrinsicSize.Max)) {
                            Icon(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(20.dp)
                                    .clickable { habitText.value = "" },
                                imageVector = TablerIcons.Minus,
                                contentDescription = "Clear habit text",
                                tint = Color.White
                            )
                            Box(
                                Modifier
                                    .background(Color(175, 53, 55))
                                    .fillMaxHeight()
                                    .width(60.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.Center)
                                        .clickable {
                                            allHabitsViewModel.updateOrGetHabit(habitName = habitText.value)
                                            navHostController.apply {
                                                navigateToRoute(
                                                    route = Destinations.HabitCreationConfirmation.path,
                                                )
                                            }
                                        },
                                    imageVector = TablerIcons.ArrowForward,
                                    contentDescription = "Proceed to save habit",
                                    tint = Color.White
                                )
                            }
                        }
                }
            }
        )
        Text(
            fontFamily = FontFamily.Serif,
            text = "${habitText.value?.length ?: 0} / $MAXIMUM_HABIT_CHARACTERS",
            color = colorResource(id = R.color.darkGray),
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
