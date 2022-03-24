package com.joblogic.domain.product.model

data class Product(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
)
