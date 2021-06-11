package com.boltic28.carassistant.data.enums

enum class Importance(val value: Int) {
    INFO(1),
    LOW(2),
    MIDDLE(3),
    HIGH(4);

    companion object {
        private val map = values().associateBy(Importance::value)
        fun fromInt(type: Int) = map[type]
    }
}