package com.boltic28.carassistant.ui.adapters.repair

import com.boltic28.carassistant.data.dto.Repair
import com.boltic28.carassistant.databinding.ItemRepairBinding
import com.boltic28.carassistant.ui.adapters.ItemHolder

class RepairItemHolder (private val binding: ItemRepairBinding): ItemHolder<Repair, ItemRepairBinding>(binding) {

    override fun bind(item: Repair){
        binding.repair = item
    }
}