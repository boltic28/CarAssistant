package com.boltic28.carassistant.ui.adapters.part

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Part

class PartAdapter : RecyclerView.Adapter<PartItemHolder>() {

    private var items = listOf<Part>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartItemHolder =
        PartItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_part,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PartItemHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}