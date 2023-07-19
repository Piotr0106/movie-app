package com.example.movieapp.domain

import android.util.Log
import retrofit2.HttpException

sealed class Error(
    val message: String,
    val throwable: Throwable? = null
)

open class NetworkError(
    message: String = "Network Error",
    throwable: Throwable? = null
) : Error(message, throwable)

fun Throwable.toError(): Error {
    return when (this) {
        is HttpException -> {
            val throwable = this
            val message = throwable.response()?.errorBody()?.string().orEmpty()
            Log.i("class error", message)
            NetworkError()
        }
        else -> NetworkError(throwable = this)
    }
}