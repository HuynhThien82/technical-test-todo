package com.joblogic.joblogictodo.product.model.mapper

import com.joblogic.domain.product.model.Product
import com.joblogic.joblogictodo.product.model.ProductModel

fun Product.toProductModel(): ProductModel{
    return ProductModel(
        id = id,
        name = name,
        price = price,
        quantity = quantity,
        type = type
    )
}

fun MutableList<Product>.toProductModelList(): MutableList<ProductModel> {
    val list = mutableListOf<ProductModel>()
    forEach {
        list.add(it.toProductModel())
    }
    return list
}