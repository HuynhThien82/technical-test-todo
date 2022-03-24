package com.joblogic.data.user.datasource.remote

import com.joblogic.data.user.datasource.remote.service.UserService
import com.joblogic.data.user.entity.mapper.toContact
import com.joblogic.data.user.entity.mapper.toContactList
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.common.repository.ErrorHandler
import com.joblogic.domain.user.model.Contact
import io.reactivex.rxjava3.core.Single

class UserRemoteDataSourceImpl(
    private val errorHandler: ErrorHandler,
    private val userService: UserService
): UserRemoteDataSource {
    override fun getCallList(): Single<ResultWrapper<MutableList<Contact>>> {
        return userService.getCallList().map<ResultWrapper<MutableList<Contact>>> { listContact ->
            ResultWrapper.Success(listContact.toContactList())
        }.onErrorReturn {
            ResultWrapper.Error(errorHandler.getError(it))
        }
    }
}