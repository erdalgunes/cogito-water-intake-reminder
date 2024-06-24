package com.cogito.hydration

import android.app.Application
import appModule
import com.cogito.core.network.di.coroutineScopesModule
import com.cogito.core.network.di.dispatchersModule
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
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@WaterIntakeTrackerApplication)

            modules(appModule, dataModule, dispatchersModule, coroutineScopesModule)
            lazyModules(presentationModule)
        }
    }
}