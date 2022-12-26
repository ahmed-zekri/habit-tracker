package com.example.habittracker.ui.mainScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.data.constants.ALERT_DIALOG_ANIMATION_DURATION_MILLIS
import com.example.habittracker.data.constants.MAXIMUM_HABIT_CHARACTERS
import com.example.habittracker.data.constants.ORIGINAL_ALERT_POSITION_X
import com.example.habittracker.data.constants.ORIGINAL_ALERT_POSITION_Y
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitConfirmAlertDialog(
    confirmHabitAlertDialogVisibility:MutableState<Boolean>,
    habitText: String
) {
    val modifier = remember {
        Modifier.background(Color(red = 219, green = 71, blue = 71))
    }
    val animatedOffset = remember {
        Animatable(ORIGINAL_ALERT_POSITION_X)
    }

    LaunchedEffect(key1 = confirmHabitAlertDialogVisibility.value) {
        animatedOffset.animateTo(
            targetValue = if (confirmHabitAlertDialogVisibility.value) ORIGINAL_ALERT_POSITION_Y else 0f,
            animationSpec = tween(
                durationMillis = ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt(), delayMillis = 0
            )
        )
    }
    Column(
        modifier = modifier.run {
            if (!confirmHabitAlertDialogVisibility.value) fillMaxSize().offset {
                    IntOffset(
                        animatedOffset.value.toInt(),
                        0
                    )
                }
            else if (animatedOffset.value != ORIGINAL_ALERT_POSITION_X) fillMaxSize().offset {
                    IntOffset(
                        animatedOffset.value.toInt(),
                        0
                    )
                }
            else size(0.dp)
        }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DialogTopBar(Icons.Default.ArrowBack) { confirmHabitAlertDialogVisibility.value = true }


        TextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(197, 62, 63), textColor = Color.White
            ),
            onValueChange = {},
            value = habitText,
        )
        Text(
            "${habitText.length} / $MAXIMUM_HABIT_CHARACTERS",
            color = Color(255, 196, 194, 255),
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}