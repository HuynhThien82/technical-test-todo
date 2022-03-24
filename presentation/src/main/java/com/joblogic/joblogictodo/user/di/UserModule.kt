package com.joblogic.joblogictodo.user.di

import com.joblogic.data.common.api.Networking
import com.joblogic.data.user.datasource.remote.UserRemoteDataSource
import com.joblogic.data.user.datasource.remote.UserRemoteDataSourceImpl
import com.joblogic.data.user.datasource.remote.service.UserService
import com.joblogic.data.user.repository.UserRepositoryImpl
import com.joblogic.domain.user.repository.UserRepository
import com.joblogic.domain.user.usecase.GetCallListUseCase
import com.joblogic.joblogictodo.user.viewmodel.ToCallViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userModule = module {
    // Data
    single<UserService> {
        Networking.getRetrofit().create(UserService::class.java)
    }
    single<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(
            errorHandler = get(),
            userService = get()
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            userRemoteDataSource = get()
        )
    }

    // Use Case
    single {
        GetCallListUseCase(
            userRepository = get(),
            executorThread = get(named("executeThread")),
            uiThread = get(named("uiThread"))
        )
    }

    // View Model
    viewModel {
        ToCallViewModel(
            getCallListUseCase = get()
        )
    }
}