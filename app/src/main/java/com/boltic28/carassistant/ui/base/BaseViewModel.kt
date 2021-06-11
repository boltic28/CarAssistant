package com.boltic28.carassistant.ui.base

import androidx.lifecycle.ViewModel
import com.boltic28.carassistant.R
import com.boltic28.carassistant.domain.messager.Messenger
import com.boltic28.carassistant.domain.sign.AppUser
import com.boltic28.carassistant.domain.sign.Auth
import com.boltic28.carassistant.domain.sign.SignState
import com.boltic28.carassistant.util.StringResourcesProvider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel(
    private val messenger: Messenger,
    private val authenticator: Auth<Task<AuthResult>>,
    private val crashlytics: FirebaseCrashlytics
): ViewModel() {

    companion object {
        const val CREATE = "create"
        const val SIGN_IN = "signIn"
    }

    //Authentication
    fun signIn(email: String, password: String): Task<AuthResult> =
        authenticator.signIn(email, password)

    fun signUp(email: String, password: String): Task<AuthResult> =
        authenticator.signUp(email, password)

    fun observeUser(): StateFlow<AppUser> = authenticator.user

    fun observeSignInState(): StateFlow<SignState> = authenticator.successSignIn

    fun signOut() = authenticator.signOut()

    fun getCurrentUser(): AppUser = authenticator.userI

    fun checkAuthTaskAndSetUser(task: Task<AuthResult>, isNewAccount: Boolean) {
        val type = if (isNewAccount) CREATE else SIGN_IN
        if (task.isSuccessful) {
            showMessage(R.string.operation_successful, type)
            authenticator.changeState(SignState.SUCCESS)
        } else {
            showMessage(R.string.operation_failed, type)
            authenticator.changeState(SignState.FAILED)
        }
    }

    //Messaging
    fun showMessage(message: String) {
        messenger.showMessage(message)
    }

    fun showMessage(stringID: Int, vararg: Any? = null){
        messenger.showMessage(stringID, vararg)
    }

    fun writeError(message: String){
        crashlytics.recordException(Exception(message))
    }
}