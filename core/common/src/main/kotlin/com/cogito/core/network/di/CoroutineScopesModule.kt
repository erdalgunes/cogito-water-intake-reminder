package com.cogito.core.network.di

import com.cogito.core.network.CogitoDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineScopesModule = module {
    single(named<CogitoDispatchers.Default>()){ CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    single(named<CogitoDispatchers.IO>()){ CoroutineScope(SupervisorJob() + Dispatchers.IO)}
}