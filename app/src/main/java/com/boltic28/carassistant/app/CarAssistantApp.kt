package com.boltic28.carassistant.app

import android.app.Application
import com.boltic28.carassistant.di.hostModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class CarAssistantApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CarAssistantApp)
            modules(
                listOf(
                    hostModule
                )
            )
        }
    }

}