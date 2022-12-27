package com.example.habittracker.ui.mainScreen

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.data.constants.MAXIMUM_HABIT_CHARACTERS
import com.example.habittracker.ui.Destinations
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HabitConfirmScreen(
    navHostController: NavHostController? = null,
) {
    val modifier = remember {
        Modifier.background(Color(red = 219, green = 71, blue = 71))
    }
    val habitText = navHostController?.currentBackStackEntry?.arguments?.getString("habitText")
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DialogTopBar(Icons.Default.ChevronLeft, "Confirm task", iconSize = 25.dp) {
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
                        .border(width = 10.dp, color = Color(102, 48, 56), shape = CircleShape)
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
                color = Color(255, 196, 194, 255),
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
                    containerColor = Color(197, 62, 63), disabledTextColor = Color.White
                ),
                onValueChange = {},
                value = habitText ?: "",
            )
            Text(
                "${habitText?.length ?: 0} / $MAXIMUM_HABIT_CHARACTERS",
                color = Color(255, 196, 194, 255),
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.Start),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                Modifier
                    .background(Color(197, 62, 63))
                    .padding(10.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(2f)
                            .align(CenterVertically)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(164, 51, 51, 255),
                                    shape = CircleShape
                                )
                                .align(CenterVertically)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                modifier = Modifier.align(
                                    Center
                                ), tint = Color.White
                            )
                        }
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
                            .background(Color(197, 62, 63))
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
                            .padding(top = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(164, 51, 51, 255),
                                    shape = CircleShape
                                )
                                .align(CenterVertically)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.align(
                                    Center
                                ), tint = Color.White
                            )
                        }
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
                            .background(Color(197, 62, 63))
                            .align(CenterVertically),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            "Every day",
                            color = Color(255, 196, 194, 255),
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
                            .padding(top = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(164, 51, 51, 255),
                                    shape = CircleShape
                                )
                                .align(CenterVertically)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircleOutline,
                                contentDescription = null,
                                modifier = Modifier.align(
                                    Center
                                ), tint = Color.White
                            )
                        }
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
                            .background(Color(197, 62, 63))
                            .align(CenterVertically),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(164, 51, 51, 255),
                                    shape = CircleShape
                                )
                                .align(CenterVertically)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = null,
                                modifier = Modifier.align(
                                    Center
                                ), tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(164, 51, 51, 255),
                                    shape = CircleShape
                                )
                                .align(CenterVertically)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.align(
                                    Center
                                ), tint = Color.White
                            )
                        }
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
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth().weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(50, 11, 12)),
            shape = ButtonDefaults.outlinedShape
        ) {
            Text(text = "Save Task", color = Color.White)
        }
    }
}