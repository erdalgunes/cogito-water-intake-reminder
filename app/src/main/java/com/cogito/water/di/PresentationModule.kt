package com.cogito.water.di

import com.cogito.water.presentation.summary.SummaryPresenter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule

val presentationModule = lazyModule {
    singleOf(::SummaryPresenter)
}