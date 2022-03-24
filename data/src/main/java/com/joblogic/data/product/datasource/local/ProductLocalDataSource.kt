package com.joblogic.data.product.datasource.local

import com.joblogic.data.product.entity.ProductEntity
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.model.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProductLocalDataSource {
    fun getSellList(): Single<ResultWrapper<MutableList<Product>>>
    fun saveItem(item: MutableList<ProductEntity>): Single<ResultWrapper<MutableList<Long>>>
}