package com.boltic28.carassistant.domain.sign

import android.os.Bundle
import kotlinx.coroutines.flow.SharedFlow

interface UserManager {

    var userI: AppUser
    val user: SharedFlow<AppUser>
    var password: String

    fun create(email: String, password: String)
    fun signIn(email: String, password: String)
    fun signOut()
    fun delete()
    fun isUserSigned(): Boolean

    fun getUserData(): Bundle
}