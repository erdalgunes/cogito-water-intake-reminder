package com.cogito.hydration.summary.presentation.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule
import com.cogito.hydration.summary.presentation.SummaryPresenter

val summaryPresentationModule = lazyModule {
    singleOf(::SummaryCircuitProviderImpl){ bind<SummaryCircuitProvider>() }
    singleOf(::SummaryPresenter)
}