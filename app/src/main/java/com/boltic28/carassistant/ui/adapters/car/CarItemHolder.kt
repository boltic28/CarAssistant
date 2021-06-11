package com.boltic28.carassistant.ui.adapters.car

import com.boltic28.carassistant.data.dto.Car
import com.boltic28.carassistant.databinding.ItemCarBinding
import com.boltic28.carassistant.ui.adapters.ItemHolder

class CarItemHolder(private val binding: ItemCarBinding): ItemHolder<Car, ItemCarBinding>(binding) {

    override fun bind(item: Car){
            binding.car = item
    }
}