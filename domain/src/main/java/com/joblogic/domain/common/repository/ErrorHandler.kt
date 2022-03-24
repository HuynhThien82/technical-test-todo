package com.joblogic.domain.common.repository

import com.joblogic.domain.common.model.ErrorType

interface ErrorHandler {
    fun getError(error: Throwable): ErrorType
}