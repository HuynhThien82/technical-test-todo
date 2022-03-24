package com.joblogic.joblogictodo.user.model

data class ContactModel(
    val id: Int,
    val name: String,
    val number: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + number.hashCode()
        return result
    }
}