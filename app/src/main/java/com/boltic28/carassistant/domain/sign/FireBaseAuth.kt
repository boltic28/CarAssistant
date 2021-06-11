package com.boltic28.carassistant.domain.sign

import android.content.SharedPreferences
import com.boltic28.carassistant.app.EMPTY_STRING
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FireBaseAuth(
    private val preferences: SharedPreferences
) : Auth<Task<AuthResult>> {

    private var auth: FirebaseAuth = Firebase.auth

    override var userI: AppUser = getLocalUser()
    override var password: String = userI.password

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow(userI)
    override val user: StateFlow<AppUser>
        get() = _user.asStateFlow()

    private val _successSignIn = MutableStateFlow(SignState.WAIT)
    override val successSignIn: StateFlow<SignState>
        get() = _successSignIn.asStateFlow()

    init {
        mAuth.addAuthStateListener {
            userI = it.currentUser?.toAppUser() ?: AppUser()
            emitUser()
        }
    }

    private fun emitUser() {
        _user.value = userI
        saveLocalUser()
    }

    override fun getLocalUser(): AppUser =
        with(preferences) {
            AppUser(
                name = getString(USER_DATA_NAME, EMPTY_STRING) ?: EMPTY_STRING,
                id = getString(USER_DATA_ID, EMPTY_STRING) ?: EMPTY_STRING,
                email = getString(USER_DATA_EMAIL, EMPTY_STRING) ?: EMPTY_STRING,
                password = getString(USER_DATA_PASSWORD, EMPTY_STRING) ?: EMPTY_STRING,
                photoUrl = getString(USER_DATA_PHOTO_URL, EMPTY_STRING) ?: EMPTY_STRING
            )
        }

    override fun saveLocalUser() {
        with(preferences.edit()) {
            putString(USER_DATA_NAME, userI.name)
            putString(USER_DATA_ID, userI.id)
            putString(USER_DATA_EMAIL, userI.email)
            putString(USER_DATA_PASSWORD, userI.password)
            putString(USER_DATA_PHOTO_URL, userI.photoUrl)
            commit()
        }
    }

    override fun clearLocalUser() {
        with(preferences.edit()) {
            putString(USER_DATA_NAME, EMPTY_STRING)
            putString(USER_DATA_ID, EMPTY_STRING)
            putString(USER_DATA_EMAIL, EMPTY_STRING)
            putString(USER_DATA_PASSWORD, EMPTY_STRING)
            putString(USER_DATA_PHOTO_URL, EMPTY_STRING)
            commit()
        }
    }

    override fun checkCurrentUser(): AppUser = auth.currentUser?.toAppUser() ?: AppUser()

    override fun signUp(email: String, password: String): Task<AuthResult> {
        this.password = password
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun signIn(email: String, password: String): Task<AuthResult> {
        this.password = password
        return mAuth.signInWithEmailAndPassword(email, password)
    }

    override fun signOut() = mAuth.signOut()

    override fun deleteUser(user: AppUser) {
        TODO("Not yet implemented")
    }

    override fun isUserSigned(): Boolean {
        return userI.id != EMPTY_STRING
    }

    override fun changeState(state: SignState) {
        _successSignIn.value = state
    }

    private fun FirebaseUser.toAppUser(): AppUser {
        return AppUser(
            this.uid,
            this.displayName ?: EMPTY_STRING,
            this.email ?: EMPTY_STRING,
            this.photoUrl.toString(),
            password
        )
    }
}