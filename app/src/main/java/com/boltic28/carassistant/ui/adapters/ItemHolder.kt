package com.boltic28.carassistant.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ItemHolder<I, B: ViewBinding> (binding: B): RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: I)
}