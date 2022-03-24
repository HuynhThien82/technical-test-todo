package com.joblogic.joblogictodo.user.model.mapper

import com.joblogic.domain.user.model.Contact
import com.joblogic.joblogictodo.user.model.ContactModel

fun Contact.toContactModel(): ContactModel{
    return ContactModel(
        id = id,
        name = name,
        number = number
    )
}

fun MutableList<Contact>.toContactModelList(): MutableList<ContactModel> {
    val result = mutableListOf<ContactModel>()
    forEach {
        result.add(it.toContactModel())
    }
    return result
}