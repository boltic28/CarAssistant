package com.boltic28.carassistant.ui.adapters.note

import com.boltic28.carassistant.data.dto.Note
import com.boltic28.carassistant.databinding.ItemNoteBinding
import com.boltic28.carassistant.ui.adapters.ItemHolder

class NoteItemHolder(private val binding: ItemNoteBinding): ItemHolder<Note, ItemNoteBinding>(binding) {

    override fun bind(item: Note){
        binding.note = item
    }
}