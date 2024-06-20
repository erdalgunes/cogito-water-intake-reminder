package com.cogito.hydration.di

import com.cogito.hydration.presentation.summary.SummaryPresenter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule

val presentationModule = lazyModule {
    singleOf(::SummaryPresenter)
}