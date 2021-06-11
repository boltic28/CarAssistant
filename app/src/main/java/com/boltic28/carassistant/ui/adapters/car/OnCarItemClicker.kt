package com.boltic28.carassistant.ui.adapters.car

import com.boltic28.carassistant.data.dto.Car

interface OnCarItemClicker {
    fun openCar(car: Car)
    fun refreshMileage(car: Car)
}