package com.joblogic.joblogictodo.product.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joblogic.joblogictodo.databinding.ProductItemBinding
import com.joblogic.joblogictodo.product.model.ProductModel

class ProductAdapter :
    ListAdapter<ProductModel, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.product = product
        }
    }
}