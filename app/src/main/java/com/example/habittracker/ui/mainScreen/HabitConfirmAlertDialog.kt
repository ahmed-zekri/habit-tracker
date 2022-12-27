package com.example.habittracker.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.data.constants.MAXIMUM_HABIT_CHARACTERS
import com.example.habittracker.ui.Destinations
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitConfirmAlertDialog(
    navHostController: NavHostController,
) {
    val modifier = remember {
        Modifier.background(Color(red = 219, green = 71, blue = 71))
    }
    val habitText = navHostController.currentBackStackEntry?.arguments?.getString("habitText")

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DialogTopBar(Icons.Default.ArrowBack) { navHostController.navigate(Destinations.HabitCreation.path) }


        TextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(197, 62, 63), textColor = Color.White
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