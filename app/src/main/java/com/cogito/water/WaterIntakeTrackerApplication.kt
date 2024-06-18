package com.cogito.water

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class WaterIntakeTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WaterIntakeTrackerApplication)
            modules(appModule)
        }
    }
}