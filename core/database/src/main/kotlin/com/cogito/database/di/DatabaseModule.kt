package com.cogito.database.di

import android.app.Application
import androidx.room.Room
import com.cogito.database.CogitoDatabase
import org.koin.dsl.module

fun daoModule() = listOf(daoModule, databaseModule)

internal val daoModule = module {
    single { provideHydrationDao(get()) }
    single { provideUserDao(get()) }
}

internal val databaseModule = module {
    single { provideDatabase(get()) }
}

internal fun provideDatabase(application: Application): CogitoDatabase =
    Room.databaseBuilder(
        application,
        CogitoDatabase::class.java,
        "cogito-database",
    ).build()

internal fun provideHydrationDao(database: CogitoDatabase) = database.hydrationDao()
internal fun provideUserDao(database: CogitoDatabase) = database.userDao()