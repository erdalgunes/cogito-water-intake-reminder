package com.cogito.analytics

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val analyticsModule = module {
    single<AnalyticsHelper> {
        StubAnalyticsHelper(
            get(parameters = { parametersOf("StubAnalyticsHelper") })
        )
    }
}