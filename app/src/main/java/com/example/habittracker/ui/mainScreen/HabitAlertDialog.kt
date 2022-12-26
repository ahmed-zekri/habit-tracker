package com.example.habittracker.ui.mainScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.data.constants.ALERT_DIALOG_ANIMATION_DURATION_MILLIS
import com.example.habittracker.data.constants.ORIGINAL_ALERT_POSITION_Y
import java.util.*

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitAlertDialog(
    visibilityState: MutableState<Boolean>? = null
) {
    val modifier = remember {
        Modifier
            .background(Color(red = 219, green = 71, blue = 71))
    }
    val openImageDialog = remember { mutableStateOf(false) }
    val iconName = remember {
        mutableStateOf<String?>(null)
    }
    val habitText = remember {
        mutableStateOf<String?>(null)
    }
    val animatedOffset = remember {
        Animatable(ORIGINAL_ALERT_POSITION_Y)
    }

    LaunchedEffect(key1 = visibilityState?.value) {
        animatedOffset.animateTo(
            targetValue = if (visibilityState?.value == true) 0f else ORIGINAL_ALERT_POSITION_Y,
            animationSpec = tween(
                durationMillis = ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt(),
                delayMillis = 0
            )
        )
    }


    Column(
        modifier = modifier.run {
            if (visibilityState?.value == false)
                if (animatedOffset.value != ORIGINAL_ALERT_POSITION_Y)
                    offset { IntOffset(x = 0, y = animatedOffset.value.toInt()) }
                else
                    size(0.dp)
            else
                offset { IntOffset(x = 0, y = animatedOffset.value.toInt()) }.fillMaxSize()
        }, horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AddTaskDialogTopBar { visibilityState?.value = false }

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
            onValueChange = { habitText.value = it },
            placeholder = { Text(text = "Enter task title...", color = Color(255, 196, 194, 255)) },
            trailingIcon = {
                habitText.value?.apply {
                    if (isNotEmpty())
                        Row(Modifier.height(IntrinsicSize.Max)) {
                            Icon(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(20.dp),
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
                                        .align(Alignment.Center),
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Proceed to save habit",
                                    tint = Color.White
                                )
                            }
                        }
                }
            }
        )
    }
    if (openImageDialog.value) {
        HabitAlertImageDialog(iconName.value, {
            openImageDialog.value = false
            iconName.value = this
        }) {
            openImageDialog.value = false
        }
    }
}