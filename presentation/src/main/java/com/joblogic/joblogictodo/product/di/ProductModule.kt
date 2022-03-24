package com.joblogic.joblogictodo.product.di

import com.joblogic.data.common.api.Networking
import com.joblogic.data.common.database.TodoAppDatabase
import com.joblogic.data.common.repository.ErrorHandlerImpl
import com.joblogic.data.product.datasource.local.ProductLocalDataSource
import com.joblogic.data.product.datasource.local.ProductLocalDataSourceImpl
import com.joblogic.data.product.datasource.local.dao.ProductDao
import com.joblogic.data.product.datasource.remote.ProductRemoteDataSource
import com.joblogic.data.product.datasource.remote.ProductRemoteDataSourceImpl
import com.joblogic.data.product.datasource.remote.service.ProductService
import com.joblogic.data.product.repository.ProductRepositoryImpl
import com.joblogic.domain.common.repository.ErrorHandler
import com.joblogic.domain.product.repository.ProductRepository
import com.joblogic.domain.product.usecase.GenerateSellListUseCase
import com.joblogic.domain.product.usecase.GetBuyListUseCase
import com.joblogic.domain.product.usecase.GetSellListUseCase
import com.joblogic.joblogictodo.product.viewmodel.ToBuyViewModel
import com.joblogic.joblogictodo.product.viewmodel.ToSellViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val productModule = module {
    // Data
    single<ProductService> {
        Networking.getRetrofit().create(ProductService::class.java)
    }
    single<ProductDao> {
        TodoAppDatabase(androidContext()).productDao()
    }
    single<ProductRemoteDataSource> {
        ProductRemoteDataSourceImpl(
            productService = get(),
            errorHandler = get()
        )
    }
    single<ProductLocalDataSource> {
        ProductLocalDataSourceImpl(
            productDao = get(),
            errorHandler = get()
        )
    }
    single<ProductRepository> {
        ProductRepositoryImpl(
            productLocalDataSource = get(),
            productRemoteDataSource = get(),
        )
    }

    // Use Case
    single {
        GetBuyListUseCase(
            productRepository = get(),
            executorThread = get(named("executeThread")),
            uiThread = get(named("uiThread"))
        )
    }
    single {
        GetSellListUseCase(
            productRepository = get(),
            executorThread = get(named("executeThread")),
            uiThread = get(named("uiThread"))
        )
    }
    single {
        GenerateSellListUseCase(
            productRepository = get(),
            executorThread = get(named("executeThread")),
            uiThread = get(named("uiThread"))
        )
    }

    // View Model
    viewModel {
        ToBuyViewModel(
            getBuyListUseCase = get()
        )
    }
    viewModel {
        ToSellViewModel(
            getSellListUseCase = get(),
            generateSellListUseCase = get()
        )
    }
}