package com.boltic28.carassistant.domain.sign

import kotlinx.coroutines.flow.StateFlow

interface Auth<R> {

    var userI: AppUser
    val user: StateFlow<AppUser>
    val successSignIn: StateFlow<SignState>
    var password: String

    fun checkCurrentUser(): AppUser
    fun signUp(email: String, password: String): R
    fun signIn(email: String, password: String): R
    fun signOut()
    fun deleteUser(user: AppUser)
    fun isUserSigned(): Boolean

    fun getLocalUser(): AppUser
    fun saveLocalUser()
    fun clearLocalUser()

    fun changeState(state: SignState)
}