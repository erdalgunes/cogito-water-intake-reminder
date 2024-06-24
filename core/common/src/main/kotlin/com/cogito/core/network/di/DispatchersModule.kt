package com.cogito.core.network.di

import com.cogito.core.network.CogitoDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single(named<CogitoDispatchers.Default>()) { Dispatchers.Default }
    single(named<CogitoDispatchers.IO>()) { Dispatchers.IO }
}