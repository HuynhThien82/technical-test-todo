package com.joblogic.data.product.datasource.remote

import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.product.model.Product
import io.reactivex.rxjava3.core.Single

interface ProductRemoteDataSource {
    fun getBuyList(): Single<ResultWrapper<MutableList<Product>>>
}