package com.cogito.hydration

import android.app.Application
import com.cogito.core.network.di.coroutineScopesModule
import com.cogito.core.network.di.dispatchersModule
import com.cogito.database.di.daoModule
import com.cogito.database.di.databaseModule
import com.cogito.hydration.summary.presentation.di.summaryPresentationModule
import com.cogito.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.lazyModules
import repositoryModule


class CogitoHydrationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@CogitoHydrationApplication)

            modules(
                coroutineScopesModule,
                daoModule,
                databaseModule,
                dispatchersModule,
                repositoryModule,
                networkModule,
            )
            lazyModules(summaryPresentationModule)
        }
    }
}