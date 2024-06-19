package com.cogito.water.di

import com.cogito.water.data.repository.WaterIntakeRepository
import com.cogito.water.presentation.summary.Summary
import com.cogito.water.presentation.summary.SummaryPresenter
import com.cogito.water.presentation.summary.state.SummaryScreen
import com.slack.circuit.foundation.Circuit

internal class CircuitProviderImpl(private val summaryPresenter: SummaryPresenter) : CircuitProvider {
    override val circuit: Circuit = Circuit.Builder()
        .addPresenter<SummaryScreen, SummaryScreen.State>(summaryPresenter)
        .addUi<SummaryScreen, SummaryScreen.State> { state, modifier -> Summary(state, modifier) }
        .build()
}