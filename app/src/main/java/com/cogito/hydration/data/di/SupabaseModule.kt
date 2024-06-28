package com.cogito.hydration.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val supabaseModule = module {
    singleOf(::SupabaseProviderImpl) { bind<SupabaseProvider>() }
    single {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }
}