package com.boltic28.carassistant.ui.adapters.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.boltic28.carassistant.R
import com.boltic28.carassistant.data.dto.Note

class NoteAdapter : RecyclerView.Adapter<NoteItemHolder>() {

    private var items = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder =
        NoteItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_note,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NoteItemHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

}