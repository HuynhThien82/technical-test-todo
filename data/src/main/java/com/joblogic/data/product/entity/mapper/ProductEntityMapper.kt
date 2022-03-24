package com.joblogic.data.product.entity.mapper

import com.joblogic.data.product.entity.ProductEntity
import com.joblogic.domain.product.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        type = type
    )
}

fun MutableList<ProductEntity>.toProductList(): MutableList<Product> {
    val result = mutableListOf<Product>()
    this.forEach {
        result.add(it.toProduct())
    }
    return result
}