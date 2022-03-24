package com.joblogic.domain.common.model

sealed class ErrorType {
    object Network: ErrorType()
    object NotFound: ErrorType()
    object AccessDenied: ErrorType()
    object ServiceUnavailable: ErrorType()
    object Unauthorized: ErrorType()
    object TimeOut: ErrorType()
    object Database: ErrorType()
    object Unknown: ErrorType()
}