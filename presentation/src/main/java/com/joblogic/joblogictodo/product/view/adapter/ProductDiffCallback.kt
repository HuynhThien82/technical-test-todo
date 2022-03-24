package com.joblogic.joblogictodo.product.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.joblogic.joblogictodo.product.model.ProductModel

class ProductDiffCallback: DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
        return oldItem == newItem
    }
}