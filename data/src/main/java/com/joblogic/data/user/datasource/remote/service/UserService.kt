package com.joblogic.data.user.datasource.remote.service

import com.joblogic.data.user.entity.ContactEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserService {
    @GET("/imkhan334/demo-1/call")
    fun getCallList(): Single<MutableList<ContactEntity>>
}