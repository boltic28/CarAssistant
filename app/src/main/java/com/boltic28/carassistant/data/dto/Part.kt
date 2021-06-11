package com.boltic28.carassistant.data.dto

import com.boltic28.carassistant.app.NO_PHOTO
import com.boltic28.carassistant.data.enums.Condition
import com.boltic28.carassistant.data.enums.PartControlType
import java.time.LocalDate

data class Part(
    val id: Long = 0,
    val carId: Long = 0,
    val mileage: Int = 0,
    val name: String = "part",
    val codes: String = "no code",
    val limitKM: Int = 10000,
    val limitDays: Int = 365,
    val dateLastChange: LocalDate = LocalDate.now(),
    val mileageLastChange: Int = 0,
    val description: String = "description",
    val photo: String = NO_PHOTO,
    val type: PartControlType = PartControlType.CHANGE,
    val condition: List<Condition> = listOf(Condition.OK),

    val notes: List<Note> = emptyList(),
    val repairs: List<Repair> = emptyList()
)
