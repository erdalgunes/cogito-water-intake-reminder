package com.cogito.core.network.di

import com.cogito.core.network.CogitoDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineScopesModule = module {
    single(named<CogitoDispatchers.Default>()){ SupervisorJob() + Dispatchers.Default}
    single(named<CogitoDispatchers.IO>()){ SupervisorJob() + Dispatchers.IO}
}