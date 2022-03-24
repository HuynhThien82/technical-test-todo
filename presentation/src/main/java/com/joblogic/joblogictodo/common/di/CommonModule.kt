package com.joblogic.joblogictodo.common.di

import com.joblogic.data.common.repository.ErrorHandlerImpl
import com.joblogic.domain.common.repository.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single<ErrorHandler> {
        ErrorHandlerImpl()
    }

    single<Scheduler>(named("executeThread")){
        Schedulers.io()
    }

    single<Scheduler>(named("uiThread")) {
        AndroidSchedulers.mainThread()
    }
}