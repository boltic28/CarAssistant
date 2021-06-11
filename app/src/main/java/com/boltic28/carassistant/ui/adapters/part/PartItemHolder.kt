package com.boltic28.carassistant.ui.adapters.part

import com.boltic28.carassistant.data.dto.Part
import com.boltic28.carassistant.databinding.ItemPartBinding
import com.boltic28.carassistant.ui.adapters.ItemHolder

class PartItemHolder(private val binding: ItemPartBinding): ItemHolder<Part, ItemPartBinding>(binding) {

    override fun bind(item: Part){
        binding.part = item
    }
}