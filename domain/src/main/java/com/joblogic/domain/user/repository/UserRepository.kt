package com.joblogic.domain.user.repository

import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.user.model.Contact
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getCallList(): Single<ResultWrapper<MutableList<Contact>>>
}