package com.joblogic.data.product.repository

import android.content.Context
import android.util.Log
import com.joblogic.data.product.datasource.local.ProductLocalDataSource
import com.joblogic.data.product.datasource.remote.ProductRemoteDataSource
import com.joblogic.data.product.entity.ProductEntity
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.model.Product
import com.joblogic.domain.product.repository.ProductRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ProductRepositoryImpl(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
) : ProductRepository {

    override fun getBuyList(): Single<ResultWrapper<MutableList<Product>>> =
        productRemoteDataSource.getBuyList()

    override fun getSellList(): Single<ResultWrapper<MutableList<Product>>> =
        productLocalDataSource.getSellList()

    override fun createDummySellList(): Single<ResultWrapper<MutableList<Long>>> {
        val dummyData = mutableListOf(
            ProductEntity(
                id = 1,
                name = "iPhone X",
                price = 150000,
                quantity = 1,
                type = 2
            ),
            ProductEntity(
                id = 2,
                name = "TV",
                price = 38000,
                quantity = 1,
                type = 2
            ),
            ProductEntity(
                id = 3,
                name = "Table",
                price = 12000,
                quantity = 1,
                type = 2
            )
        )
        return productLocalDataSource.saveItem(dummyData)
    }

    companion object {
        private val TAG = this::class.java.name
    }
}