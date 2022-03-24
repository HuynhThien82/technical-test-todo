package com.joblogic.data.product.datasource.remote

import com.joblogic.data.product.datasource.remote.service.ProductService
import com.joblogic.data.product.entity.mapper.toProduct
import com.joblogic.data.product.entity.mapper.toProductList
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.common.repository.ErrorHandler
import com.joblogic.domain.product.model.Product
import io.reactivex.rxjava3.core.Single

class ProductRemoteDataSourceImpl(
    private val productService: ProductService,
    private val errorHandler: ErrorHandler
): ProductRemoteDataSource {
    override fun getBuyList(): Single<ResultWrapper<MutableList<Product>>> {
        return productService.getBuyList().map<ResultWrapper<MutableList<Product>>> { list ->
            ResultWrapper.Success(list.toProductList())
        }.onErrorReturn {
            ResultWrapper.Error(errorHandler.getError(it))
        }
    }
}