package com.joblogic.data.product.datasource.remote.service

import com.joblogic.data.product.entity.ProductEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ProductService {
    @GET("/imkhan334/demo-1/buy")
    fun getBuyList(): Single<MutableList<ProductEntity>>
}