package com.boltic28.carassistant.data.dto

import com.boltic28.carassistant.data.enums.Importance
import java.time.LocalDate

data class Note(
    val id: Long = 0,
    val carId: Long = 0,
    val mileage: Int = 0,
    val partId: Long = 0,
    val description: String = "some note",
    val date: LocalDate = LocalDate.now(),
    val importantLevel: Importance = Importance.INFO
)