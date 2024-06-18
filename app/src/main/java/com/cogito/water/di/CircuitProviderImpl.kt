package com.cogito.water.di

import com.cogito.water.presentation.summary.Summary
import com.cogito.water.presentation.summary.SummaryPresenter
import com.cogito.water.screen.state.SummaryScreen
import com.slack.circuit.foundation.Circuit

internal class CircuitProviderImpl : CircuitProvider {
    override val circuit: Circuit = Circuit.Builder()
        .addPresenter<SummaryScreen, SummaryScreen.State>(SummaryPresenter())
        .addUi<SummaryScreen, SummaryScreen.State> { state, modifier -> Summary(state, modifier) }
        .build()
}