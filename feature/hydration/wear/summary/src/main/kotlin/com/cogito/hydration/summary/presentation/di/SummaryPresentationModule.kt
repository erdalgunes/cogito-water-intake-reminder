package com.cogito.hydration.summary.presentation.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule
import com.cogito.hydration.summary.presentation.SummaryPresenter
import domainModule
import org.koin.dsl.module

fun summaryPresentationModule() = summaryPresentationModule + domainModule()

internal val summaryPresentationModule = module {
    singleOf(::SummaryCircuitProviderImpl){ bind<SummaryCircuitProvider>() }
    singleOf(::SummaryPresenter)
}
