package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.example.habittracker.ui.screenUtils.RoundedIcon
import com.example.habittracker.ui.screenUtils.ScreenTopBar
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitConfirmScreen(
    navHostController: NavHostController? = null,
    habitCreationViewModel: HabitCreationViewModel
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier.background(color = primaryColor)
    }
    val habitText = habitCreationViewModel.updateOrGetHabit()?.name
    val icon = habitCreationViewModel.updateOrGetHabit()?.icon ?: ""
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTopBar(TablerIcons.ChevronLeft, "Confirm task", iconSize = 25.dp) {
                navHostController?.navigateToRoute(
                    Destinations.HabitCreation.path
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .size(130.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .border(
                            width = 10.dp,
                            color = colorResource(id = R.color.darkerGray),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = buildList {
                            addAll(FontAwesomeIcons.AllIcons)
                            addAll(TablerIcons.AllIcons)
                        }.find { it.name == icon }
                            ?: TablerIcons.Brush,
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Center),
                        tint = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White, shape = CircleShape)
                        .align(Alignment.BottomEnd)
                        .clickable { navHostController?.navigateToRoute(Destinations.HabitIconSelection.path) },
                    horizontalArrangement = Arrangement.Center
                ) {
                    (1..3).forEach { _ ->
                        Box(
                            modifier = Modifier
                                .background(Color.Black, CircleShape)
                                .size(7.dp)
                                .align(CenterVertically)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontFamily = FontFamily.Serif, text = habitText ?: "",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                fontFamily = FontFamily.Serif, text = "Title",
                color = colorResource(id = R.color.darkGray),
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.Start),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = colorResource(id = R.color.primaryDarkLighter),
                    disabledTextColor = Color.White
                ),
                onValueChange = {},
                value = habitText ?: "",
            )
            Text(
                fontFamily = FontFamily.Serif,
                text = "${habitText?.length ?: 0} / $MAXIMUM_HABIT_CHARACTERS",
                color = colorResource(id = R.color.darkGray),
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.Start),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .padding(10.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable { navHostController?.navigate(Destinations.HabitDurationMeasurement.path) }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(2.8f)
                            .align(CenterVertically)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = TablerIcons.Calendar
                        )

                        Text(
                            fontFamily = FontFamily.Serif, text = "Daily task",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp, modifier = Modifier.align(CenterVertically)
                        )
                    }
                    Row(
                        modifier
                            .weight(3.8f)
                            .background(colorResource(id = R.color.primaryDarkLighter))
                            .align(CenterVertically),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = TablerIcons.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(40.dp), tint = Color.White
                        )
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { navHostController?.navigate(Destinations.HabitTaskDays.path) }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(2.7f)
                            .align(CenterVertically)
                            .padding(vertical = 10.dp)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = TablerIcons.CalendarTime
                        )

                        Text(
                            fontFamily = FontFamily.Serif, text = "Task days",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp, modifier = Modifier.align(CenterVertically)
                        )
                    }
                    Row(
                        modifier
                            .weight(3.8f)
                            .background(colorResource(id = R.color.primaryDarkLighter))
                            .align(CenterVertically),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            fontFamily = FontFamily.Serif, text = "Every day",
                            color = colorResource(id = R.color.darkGray),
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .align(CenterVertically),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = TablerIcons.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(40.dp), tint = Color.White
                        )
                    }
                }

                Row(Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(2.8f)
                            .align(CenterVertically)
                            .padding(vertical = 10.dp)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = TablerIcons.CircleCheck
                        )
                        Text(
                            fontFamily = FontFamily.Serif, text = "1 time/day",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp, modifier = Modifier.align(CenterVertically)
                        )
                    }
                    Row(
                        modifier
                            .weight(3.8f)
                            .background(colorResource(id = R.color.primaryDarkLighter))
                            .align(CenterVertically),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = TablerIcons.Minus
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = TablerIcons.Plus
                        )
                        Icon(
                            imageVector = TablerIcons.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier
                                .align(CenterVertically)
                                .size(40.dp), tint = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(3.7f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = TablerIcons.Notification
                    )

                    Text(
                        fontFamily = FontFamily.Serif, text = "Notifications",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp, modifier = Modifier.align(CenterVertically)
                    )
                }
                Row(
                    modifier
                        .weight(3.8f)
                        .background(colorResource(id = R.color.primaryDarkLighter))
                        .align(CenterVertically),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = TablerIcons.ChevronRight,
                        contentDescription = null,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(40.dp), tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(3.7f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = TablerIcons.Activity
                    )

                    Text(
                        fontFamily = FontFamily.Serif, text = "Action Button",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp, modifier = Modifier.align(CenterVertically)
                    )
                }
                Row(
                    modifier
                        .weight(3.8f)
                        .background(colorResource(id = R.color.primaryDarkLighter))
                        .align(CenterVertically),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        fontFamily = FontFamily.Serif, text = "None",
                        color = colorResource(id = R.color.darkGray),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .align(CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = TablerIcons.ChevronRight,
                        contentDescription = null,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(40.dp), tint = Color.White
                    )
                }
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkestTone)),
            shape = ButtonDefaults.outlinedShape
        ) {
            Text(text = "Save Task", color = Color.White)
        }
    }
}