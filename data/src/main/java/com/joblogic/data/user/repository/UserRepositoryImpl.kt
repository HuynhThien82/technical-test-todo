package com.joblogic.data.user.repository

import com.joblogic.data.user.datasource.remote.UserRemoteDataSource
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.user.model.Contact
import com.joblogic.domain.user.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
    // userLocalDataSource if needed
) : UserRepository {
    override fun getCallList(): Single<ResultWrapper<MutableList<Contact>>> =
        userRemoteDataSource.getCallList()
}