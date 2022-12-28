package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.utils.navigateToRoute
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.screenUtils.RoundedIcon
import com.example.habittracker.ui.screenUtils.ScreenTopBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.CalendarPlus
import compose.icons.fontawesomeicons.regular.CheckCircle

@Composable
fun HabitDurationMeasurementScreen(
    navHostController: NavHostController? = null,
    habitCreationViewModel: HabitCreationViewModel
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier.background(color = primaryColor)
    }

    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTopBar(Icons.Default.ChevronLeft, "Duration measurement", iconSize = 25.dp) {
                navHostController?.navigateToRoute(
                    Destinations.HabitCreation.path
                )
            }


            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Determines the period of time a single completion is measured over",
                color = colorResource(id = R.color.darkGray),
                modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                Modifier
                    .background(colorResource(id = R.color.primaryDarkLighter))
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(2.7f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = FontAwesomeIcons.Regular.CalendarPlus
                    )

                    Text(
                        text = "Daily Task",
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
                        imageVector = FontAwesomeIcons.Regular.CheckCircle,
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
                        .weight(2.7f)
                        .align(CenterVertically)
                ) {
                    RoundedIcon(
                        modifier = Modifier.size(50.dp),
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = Icons.Default.CalendarViewMonth
                    )

                    Text(
                        text = "Weekly Task",
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
                        imageVector = FontAwesomeIcons.Regular.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(40.dp), tint = Color.White
                    )
                }
            }


        }
    }
}