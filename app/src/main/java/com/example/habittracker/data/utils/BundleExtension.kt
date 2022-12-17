package com.example.habittracker.data.utils

import android.os.Build
import android.os.Bundle

fun <T> Bundle.getParcelableData(
    key: String,
    clazz: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        getParcelable(
            key,
            clazz
        )
    else
        getParcelable(key) as T?
