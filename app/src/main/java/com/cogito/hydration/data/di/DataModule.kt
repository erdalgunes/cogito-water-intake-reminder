package com.cogito.hydration.data.di

import com.cogito.hydration.data.repository.HydrationRepository
import com.cogito.hydration.data.repository.HydrationRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::SupabaseProviderImpl) { bind<SupabaseProvider>() }
    singleOf(::HydrationRepositoryImpl) { bind<HydrationRepository>() }
    single {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }
}