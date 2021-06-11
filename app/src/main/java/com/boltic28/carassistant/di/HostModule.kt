package com.boltic28.carassistant.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.loader.ResourcesProvider
import com.boltic28.carassistant.app.PREFERENCE_NAME
import com.boltic28.carassistant.domain.messager.Messenger
import com.boltic28.carassistant.domain.sign.Auth
import com.boltic28.carassistant.domain.sign.FireBaseAuth
import com.boltic28.carassistant.ui.LoginViewModel
import com.boltic28.carassistant.ui.MainActivityViewModel
import com.boltic28.carassistant.ui.MainFragmentViewModel
import com.boltic28.carassistant.ui.SplashViewModel
import com.boltic28.carassistant.util.StringResourcesProvider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val hostModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
    }

    single<Auth<Task<AuthResult>>>{
        FireBaseAuth(
            preferences = get()
        )
    }

    single<Messenger> {
        Messenger(
            context = androidContext()
        )
    }

    single<FirebaseCrashlytics> {
        FirebaseCrashlytics.getInstance()
    }

    viewModel {
        SplashViewModel(
            messenger = get(),
            authenticator = get(),
            crashlytics = get()
        )
    }

    viewModel {
        LoginViewModel(
            messenger = get(),
            authenticator = get(),
            crashlytics = get()
        )
    }

    viewModel {
        MainActivityViewModel(
            messenger = get(),
            authenticator = get(),
            crashlytics = get()
        )
    }

    viewModel {
        MainFragmentViewModel(
            messenger = get(),
            authenticator = get(),
            crashlytics = get()
        )
    }
}