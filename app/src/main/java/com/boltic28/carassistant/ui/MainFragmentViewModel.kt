package com.boltic28.carassistant.ui

import com.boltic28.carassistant.domain.messager.Messenger
import com.boltic28.carassistant.domain.sign.Auth
import com.boltic28.carassistant.ui.base.BaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainFragmentViewModel(
    messenger: Messenger,
    authenticator: Auth<Task<AuthResult>>,
    crashlytics: FirebaseCrashlytics
) : BaseViewModel(
    messenger = messenger,
    authenticator = authenticator,
    crashlytics = crashlytics
)