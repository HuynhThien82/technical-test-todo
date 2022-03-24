package com.joblogic.data.common.repository

import android.database.sqlite.SQLiteException
import android.util.Log
import com.joblogic.domain.common.model.ErrorType
import com.joblogic.domain.common.repository.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(error: Throwable): ErrorType {
        Log.d(TAG, "getError: ${error.message}")
        return when(error) {
            is IOException -> ErrorType.Network
            is UnknownHostException -> ErrorType.Network
            is SocketTimeoutException -> ErrorType.TimeOut
            is HttpException -> {
                when(error.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorType.NotFound
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorType.AccessDenied
                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorType.Unauthorized
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorType.ServiceUnavailable
                    else -> ErrorType.Unknown
                }
            }
            is SQLiteException -> ErrorType.Database
            else -> ErrorType.Unknown
        }
    }

    companion object {
        private val TAG = this::class.java.name
    }
}