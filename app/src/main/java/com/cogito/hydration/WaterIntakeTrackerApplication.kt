package com.cogito.hydration

import android.app.Application
import appModule
import com.cogito.hydration.data.di.dataModule
import com.cogito.hydration.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.lazyModules


class WaterIntakeTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WaterIntakeTrackerApplication)
            modules(appModule, dataModule)
            lazyModules(presentationModule)
        }
    }
}