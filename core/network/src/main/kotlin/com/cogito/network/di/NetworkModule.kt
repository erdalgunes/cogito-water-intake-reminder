package com.cogito.network.di

import com.cogito.network.datasource.hydration.HydrationNetworkDataSource
import com.cogito.network.datasource.hydration.HydrationNetworkDataSourceImpl
import com.cogito.network.datasource.user.UserNetworkDataSource
import com.cogito.network.datasource.user.UserNetworkDataSourceImpl
import com.cogito.network.supabase.SupabaseProvider
import com.cogito.network.supabase.SupabaseProviderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val networkModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single<UserNetworkDataSource> {
        UserNetworkDataSourceImpl(
            supabaseProvider = get(),
            log = get(parameters = { parametersOf("UserNetworkDataSourceImpl") })
        )
    }
    singleOf(::HydrationNetworkDataSourceImpl) { bind<HydrationNetworkDataSource>() }
    singleOf(::SupabaseProviderImpl) { bind<SupabaseProvider>() }
}