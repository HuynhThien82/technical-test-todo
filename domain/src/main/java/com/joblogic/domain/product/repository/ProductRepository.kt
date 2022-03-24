package com.joblogic.domain.product.repository

import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.model.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getBuyList(): Single<ResultWrapper<MutableList<Product>>>
    fun getSellList(): Single<ResultWrapper<MutableList<Product>>>
    fun createDummySellList(): Single<ResultWrapper<MutableList<Long>>>
}