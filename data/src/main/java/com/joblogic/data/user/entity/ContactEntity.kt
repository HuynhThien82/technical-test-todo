package com.joblogic.data.user.entity

import com.google.gson.annotations.SerializedName

data class ContactEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String
)