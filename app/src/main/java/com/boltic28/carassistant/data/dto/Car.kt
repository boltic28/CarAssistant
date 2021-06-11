package com.boltic28.carassistant.data.dto

import com.boltic28.carassistant.app.NO_PHOTO
import com.boltic28.carassistant.data.enums.Condition
import java.time.LocalDate

data class Car (
    val id: Long = 0,
    val brand: String = "brand",
    val model: String = "model",
    val number: String = "0000 AA-7",
    val vin: String = "VIN CODE",
    val year: Int = 1980,
    val mileage: Int = 0,
    val whenMileageRefreshed: LocalDate = LocalDate.now(),
    val condition: Condition = Condition.OK,
    val photo: String = NO_PHOTO,

    val parts: List<Part> = emptyList(),
    val notes: List<Note> = emptyList(),
    val repairs: List<Repair> = emptyList()
)