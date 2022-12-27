package com.example.habittracker.ui.HabitCreationScreen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.habittracker.data.utils.navigate
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.ScreenTopBar
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitCreationScreen(
    navHostController: NavHostController
) {
    val primaryColor = colorResource(id = R.color.primary)
    val modifier = remember {
        Modifier
            .background(primaryColor)
    }
    val habitText = remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ScreenTopBar(
            Icons.Default.Close,
            "Add task"
        ) { navHostController.navigate(Destinations.Home.path) }

        Icon(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .size(55.dp),
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Customized habit",
            tint = Color.White
        )

        Text(
            "Tasks start each day as incomplete , Mark a task as done to increase your streak",
            color = colorResource(id = R.color.darkGray),
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            "CREATE YOUR OWN:",
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
            placeholder = { Text(text = "Enter task title...", color = colorResource(id = R.color.darkGray)) },
            trailingIcon = {
                habitText.value?.apply {
                    if (isNotEmpty())
                        Row(Modifier.height(IntrinsicSize.Max)) {
                            Icon(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(20.dp)
                                    .clickable { habitText.value = "" },
                                imageVector = Icons.Default.Delete,
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
                                            navHostController.apply {
                                                navigate(
                                                    route = Destinations.HabitCreationConfirmation.path,
                                                    args = Bundle().apply {
                                                        putString("habitText", habitText.value)
                                                    })
                                            }
                                        },
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Proceed to save habit",
                                    tint = Color.White
                                )
                            }
                        }
                }
            }
        )
        Text(
            "${habitText.value?.length ?: 0} / $MAXIMUM_HABIT_CHARACTERS",
            color = colorResource(id = R.color.darkGray),
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start), fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
