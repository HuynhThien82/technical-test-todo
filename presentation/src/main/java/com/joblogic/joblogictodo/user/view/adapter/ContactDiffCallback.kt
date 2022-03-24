package com.joblogic.joblogictodo.user.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.joblogic.joblogictodo.user.model.ContactModel

class ContactDiffCallback: DiffUtil.ItemCallback<ContactModel>() {
    override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel): Boolean {
        return oldItem == newItem
    }
}