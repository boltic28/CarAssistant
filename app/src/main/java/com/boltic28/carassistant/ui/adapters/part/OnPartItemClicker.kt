package com.boltic28.carassistant.ui.adapters.part

import com.boltic28.carassistant.data.dto.Part

interface OnPartItemClicker {
    fun openItem(part: Part)
}