package com.joblogic.domain.common.usecase

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCaseWithParam<T, in Params>(
    private val executorThread: Scheduler,
    private val uiThread: Scheduler
) {
    operator fun invoke(params: Params): Single<T> {
        return create(params)
            .subscribeOn(executorThread)
            .observeOn(uiThread)
    }

    protected abstract fun create(params: Params): Single<T>
}