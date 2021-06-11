package com.boltic28.carassistant.domain.sign

import com.boltic28.carassistant.app.EMPTY_STRING

const val USER_DATA_ID = "user_id"
const val USER_DATA_NAME = "user_name"
const val USER_DATA_EMAIL = "user_email"
const val USER_DATA_PHOTO_URL = "user_photo"
const val USER_DATA_PASSWORD = "user_password"

data class AppUser(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val photoUrl: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)