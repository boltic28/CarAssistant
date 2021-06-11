package com.boltic28.carassistant.domain.sign

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class AuthContract : ActivityResultContract<Intent?, AppUser>() {

    private val providers = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    override fun createIntent(context: Context, input: Intent?): Intent =
        input ?: AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

    override fun parseResult(resultCode: Int, intent: Intent?): AppUser {
        val response = IdpResponse.fromResultIntent(intent)
        if (intent == null) return AppUser()
        val fbUser = FirebaseAuth.getInstance().currentUser
        val user1 = FirebaseAuth.getInstance().currentUser
        println("->> user1 is: $user1")
        println("->> fbUser is: $fbUser")
        val user = if (resultCode == Activity.RESULT_OK) {
            AppUser()
            //FirebaseAuth.getInstance().currentUser
        } else {
            AppUser()

        }

        return user
    }
}