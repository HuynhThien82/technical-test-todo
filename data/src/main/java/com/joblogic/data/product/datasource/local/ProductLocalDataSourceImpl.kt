package com.joblogic.data.product.datasource.local

import com.joblogic.data.product.datasource.local.dao.ProductDao
import com.joblogic.data.product.entity.ProductEntity
import com.joblogic.data.product.entity.mapper.toProductList
import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.common.repository.ErrorHandler
import com.joblogic.domain.product.model.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ProductLocalDataSourceImpl(
    private val productDao: ProductDao,
    private val errorHandler: ErrorHandler
) : ProductLocalDataSource {
    override fun getSellList(): Single<ResultWrapper<MutableList<Product>>> {
        return productDao.getSellList().map<ResultWrapper<MutableList<Product>>> {
            ResultWrapper.Success(it.toProductList())
        }.onErrorReturn {
            ResultWrapper.Error(errorHandler.getError(it))
        }
    }

    override fun saveItem(item: MutableList<ProductEntity>): Single<ResultWrapper<MutableList<Long>>> {
        return productDao.saveItem(item).map<ResultWrapper<MutableList<Long>>> {
            ResultWrapper.Success(it)
        }.onErrorReturn {
            ResultWrapper.Error(errorHandler.getError(it))
        }
    }

}