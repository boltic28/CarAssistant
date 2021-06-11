package com.boltic28.carassistant.data.dto

import java.time.LocalDate

data class Repair (
    val id: Long = 0,
    val carId: Long = 0,
    val partId: Long = 0,
    val type: String = "type",
    val cost: Int = 0,
    val mileage: Int = 0,
    val description: String = "some repair",
    val date: LocalDate = LocalDate.now()
)