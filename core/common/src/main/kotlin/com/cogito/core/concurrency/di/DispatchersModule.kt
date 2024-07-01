package com.cogito.core.concurrency.di

import com.cogito.core.concurrency.CogitoDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val dispatchersModule = module {
    single(named<CogitoDispatchers.Default>()) { Dispatchers.Default }
    single(named<CogitoDispatchers.IO>()) { Dispatchers.IO }
}