package com.joblogic.domain.product.usecase

import com.joblogic.domain.common.model.ResultWrapper
import com.joblogic.domain.common.usecase.SingleUseCase
import com.joblogic.domain.product.model.Product
import com.joblogic.domain.product.repository.ProductRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class GetBuyListUseCase(
    private val productRepository: ProductRepository,
    executorThread: Scheduler,
    uiThread: Scheduler
) : SingleUseCase<ResultWrapper<MutableList<Product>>>(executorThread, uiThread) {
    override fun create(): Single<ResultWrapper<MutableList<Product>>> =
        productRepository.getBuyList()
}