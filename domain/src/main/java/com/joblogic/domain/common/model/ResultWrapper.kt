package com.joblogic.domain.common.model

sealed class ResultWrapper<T> {
    data class Success<T>(val data: T): ResultWrapper<T>()
    data class Error<T>(val error: ErrorType): ResultWrapper<T>()
}
