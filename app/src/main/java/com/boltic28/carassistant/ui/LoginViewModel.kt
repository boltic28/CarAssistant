package com.boltic28.carassistant.ui

import com.boltic28.carassistant.R
import com.boltic28.carassistant.app.MIN_PASSWORD_DIGIT
import com.boltic28.carassistant.app.MIN_PASSWORD_LENGTH
import com.boltic28.carassistant.domain.messager.Messenger
import com.boltic28.carassistant.domain.sign.Auth
import com.boltic28.carassistant.ui.base.BaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.crashlytics.FirebaseCrashlytics

class LoginViewModel(
    messenger: Messenger,
    authenticator: Auth<Task<AuthResult>>,
    crashlytics: FirebaseCrashlytics
) : BaseViewModel(
    messenger = messenger,
    authenticator = authenticator,
    crashlytics = crashlytics
) {

    fun isSignInDataValid(email: String, password: String): Boolean =
        isPasswordValid(password) && isEmailValid(email)

    fun isRegistrationDataValid(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Boolean =
        isEmailValid(email) &&
        isConfirmationPassed(password, passwordConfirmation) &&
                isPasswordValid(password)

    private fun isConfirmationPassed(password: String, passwordConfirmation: String): Boolean =
        if (password != passwordConfirmation) {
            showMessage(R.string.password_is_not_confirmed)
            false
        } else {
            true
        }

    private fun isPasswordValid(password: String): Boolean =
        (if (password.isEmpty()) {
            showMessage(R.string.password_is_not_filled)
            false
        } else {
            true
        }) &&
        (if (password.length < MIN_PASSWORD_LENGTH) {
            showMessage(R.string.password_is_too_short)
            false
        } else {
            true
        })
//                &&
//        (if (password.contains('d')) {
//            true
//        } else {
//            showMessage(R.string.password_has_to_contain_digit, MIN_PASSWORD_DIGIT)
//            false
//        })

    private fun isEmailValid(email: String): Boolean =
        (if (email.isEmpty()) {
            showMessage(R.string.email_is_not_filled)
            false
        } else {
            true
        }) &&
        (if (email.contains("@") && email.contains(".")) {
            true
        } else {
            showMessage(R.string.email_seems_wrong)
            false
        })
}
