package com.example.habittracker.data.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.example.habittracker.data.constants.ALERT_DIALOG_ANIMATION_DURATION_MILLIS

fun verticalAnimation(): Pair<EnterTransition, ExitTransition> =
    Pair(
        slideInVertically(
            animationSpec = tween(
                ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
            )
        ), slideOutVertically(
            animationSpec = tween(
                ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
            )
        )

    )

fun horizontalAnimation(): Pair<EnterTransition, ExitTransition> =
    Pair(
        slideInHorizontally(
            animationSpec = tween(
                ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
            )
        ), slideOutHorizontally(
            animationSpec = tween(
                ALERT_DIALOG_ANIMATION_DURATION_MILLIS.toInt()
            )
        )

    )
