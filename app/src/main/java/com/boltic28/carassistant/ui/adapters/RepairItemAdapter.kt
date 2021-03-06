package com.boltic28.carassistant.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Repair

class RepairItemAdapter(repairsIn: List<Repair>, private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<RepairItemAdapter.RepairHolder>() {

    private var repairs = repairsIn

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepairHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repair, parent, false))

    override fun getItemCount() = repairs.size

    override fun onBindViewHolder(holder: RepairHolder, position: Int) = holder.bind(repairs[position])

        inner class RepairHolder(private val repairRow: View) : RecyclerView.ViewHolder(repairRow) {

            private val date = repairRow.findViewById<TextView>(R.id.repairItemDate)
            private val mileage = repairRow.findViewById<TextView>(R.id.repairItemMileage)
            private val description = repairRow.findViewById<TextView>(R.id.repairItemDescription)
            private val type = repairRow.findViewById<TextView>(R.id.repairItemType)

            fun bind(repair: Repair) {
                date.text = repair.date.toString()
               // mileage.text = repairRow.resources.getString(R.string.car_item_normal_mileage, repair.mileage)
                type.text = repair.type
                description.text = repair.description

                repairRow.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onClick(repairs[adapterPosition])
                    }
                }
            }
        }

    interface OnItemClickListener {
        fun onClick(repair: Repair)
    }
}