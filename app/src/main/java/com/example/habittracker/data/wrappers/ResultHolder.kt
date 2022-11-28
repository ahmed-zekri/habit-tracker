package com.example.habittracker.data.wrappers

sealed class ResultHolder<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T) : ResultHolder<T>(data = data)
    class Error<T>(error: String) : ResultHolder<T>(error = error)
}
