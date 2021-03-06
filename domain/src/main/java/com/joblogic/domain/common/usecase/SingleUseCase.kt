package com.joblogic.domain.common.usecase

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<T>(private val executorThread: Scheduler,
                                private val uiThread: Scheduler) {
    operator fun invoke(): Single<T> {
        return create()
            .subscribeOn(executorThread)
            .observeOn(uiThread)
    }

    protected abstract fun create(): Single<T>
}