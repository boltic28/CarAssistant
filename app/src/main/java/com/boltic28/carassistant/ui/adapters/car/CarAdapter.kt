package com.boltic28.carassistant.ui.adapters.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Car

class CarAdapter(private val items: List<Car>) : RecyclerView.Adapter<CarItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemHolder =
        CarItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_car,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CarItemHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}