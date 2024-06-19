package com.cogito.water.data.di

import android.system.Os.bind
import com.cogito.water.data.repository.WaterIntakeRepository
import com.cogito.water.data.repository.WaterIntakeRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.single

val dataModule = module {
    singleOf(::SupabaseProviderImpl) { bind<SupabaseProvider>() }
    singleOf(::WaterIntakeRepositoryImpl) { bind<WaterIntakeRepository>() }
    single {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }
}