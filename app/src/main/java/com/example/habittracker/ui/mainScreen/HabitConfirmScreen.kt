package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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
                        .align(Alignment.Center),
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
    }
}