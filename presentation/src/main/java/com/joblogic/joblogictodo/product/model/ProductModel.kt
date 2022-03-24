package com.joblogic.joblogictodo.product.model

data class ProductModel(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int,
    val type: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (quantity != other.quantity) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price
        result = 31 * result + quantity
        result = 31 * result + type
        return result
    }

}
