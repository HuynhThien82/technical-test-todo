package com.joblogic.domain.user.usecase

import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.common.usecase.SingleUseCase
import com.joblogic.domain.user.model.Contact
import com.joblogic.domain.user.repository.UserRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class GetCallListUseCase(
    private val userRepository: UserRepository,
    executorThread: Scheduler,
    uiThread: Scheduler
) : SingleUseCase<ResultWrapper<MutableList<Contact>>>(executorThread, uiThread) {
    override fun create(): Single<ResultWrapper<MutableList<Contact>>> =
        userRepository.getCallList()
}