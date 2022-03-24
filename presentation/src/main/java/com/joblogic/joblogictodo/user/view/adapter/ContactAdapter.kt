package com.joblogic.joblogictodo.user.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joblogic.joblogictodo.databinding.ContactItemBinding
import com.joblogic.joblogictodo.user.model.ContactModel

class ContactAdapter :
    ListAdapter<ContactModel, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactViewHolder(
        private val binding: ContactItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactModel) {
            binding.contact = contact
        }
    }
}