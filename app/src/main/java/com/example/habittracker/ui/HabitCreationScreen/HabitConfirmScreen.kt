package com.example.habittracker.ui.HabitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.constants.MAXIMUM_HABIT_CHARACTERS
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.ScreenTopBar
import com.example.habittracker.ui.RoundedIcon
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitConfirmScreen(
    navHostController: NavHostController? = null,
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier.background(color = primaryColor)
    }
    val habitText = navHostController?.currentBackStackEntry?.arguments?.getString("habitText")
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTopBar(Icons.Default.ChevronLeft, "Confirm task", iconSize = 25.dp) {
                navHostController?.navigate(
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
                        imageVector = Icons.Default.Brush,
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
                        .align(Alignment.BottomEnd), horizontalArrangement = Arrangement.Center
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
                text = habitText ?: "",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                "Title",
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
                "${habitText?.length ?: 0} / $MAXIMUM_HABIT_CHARACTERS",
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
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(2f)
                            .align(CenterVertically)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = Icons.Default.CalendarToday
                        )

                        Text(
                            text = "Daily task",
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
                            imageVector = Icons.Default.ChevronRight,
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
                            .weight(2f)
                            .align(CenterVertically)
                            .padding(vertical = 10.dp)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = Icons.Default.CalendarMonth
                        )

                        Text(
                            text = "Task days",
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
                            "Every day",
                            color = colorResource(id = R.color.darkGray),
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .align(CenterVertically),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
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
                            .weight(2f)
                            .align(CenterVertically)
                            .padding(vertical = 10.dp)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = Icons.Default.CheckCircle
                        )
                        Text(
                            text = "1 time/day",
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
                            icon = Icons.Default.Remove
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        RoundedIcon(
                            modifier = Modifier.size(50.dp),
                            circleColor = colorResource(id = R.color.primaryDark),
                            icon = Icons.Default.Add
                        )
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
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
                        .weight(2.4f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = Icons.Default.Notifications
                    )

                    Text(
                        text = "Notifications",
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
                        imageVector = Icons.Default.ChevronRight,
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
                        .weight(2.5f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = Icons.Default.CallToAction
                    )

                    Text(
                        text = "Action Button",
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
                        "None",
                        color = colorResource(id = R.color.darkGray),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .align(CenterVertically),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
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