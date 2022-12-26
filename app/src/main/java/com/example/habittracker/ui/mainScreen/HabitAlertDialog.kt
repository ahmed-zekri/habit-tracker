package com.example.habittracker.ui.mainScreen

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
fun HabitAlertDialog(
    navHostController: NavHostController
) {
    val modifier = remember {
        Modifier
            .background(Color(red = 219, green = 71, blue = 71))
    }
    val habitText = remember {
        mutableStateOf<String?>(null)
    }
    val confirmHabitAlertDialogVisibility = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        DialogTopBar(Icons.Default.Close) { navHostController.navigate(Destinations.Home.path) }

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
            color = Color(255, 196, 194, 255),
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            "CREATE YOUR OWN:",
            color = Color(255, 196, 194, 255),
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
            placeholder = { Text(text = "Enter task title...", color = Color(255, 196, 194, 255)) },
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
                                            confirmHabitAlertDialogVisibility.value = true
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
            color = Color(255, 196, 194, 255),
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start), fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }

}