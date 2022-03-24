package com.joblogic.data.user.entity.mapper

import com.joblogic.data.user.entity.ContactEntity
import com.joblogic.domain.user.model.Contact

fun ContactEntity.toContact(): Contact {
    return Contact(
        id = id,
        name = name,
        number = number
    )
}

fun MutableList<ContactEntity>.toContactList(): MutableList<Contact> {
    val result = mutableListOf<Contact>()
    this.forEach {
        result.add(it.toContact())
    }
    return result
}