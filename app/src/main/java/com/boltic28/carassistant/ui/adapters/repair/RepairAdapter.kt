package com.boltic28.carassistant.ui.adapters.repair

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Repair

class RepairAdapter: RecyclerView.Adapter<RepairItemHolder>() {

    private var items = listOf<Repair>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepairItemHolder =
        RepairItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_repair,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepairItemHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}