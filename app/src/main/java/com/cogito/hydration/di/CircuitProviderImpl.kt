package com.cogito.hydration.di

import com.cogito.hydration.presentation.summary.Summary
import com.cogito.hydration.presentation.summary.SummaryPresenter
import com.cogito.hydration.presentation.summary.state.SummaryScreen
import com.cogito.hydration.presentation.summary.state.SummaryState
import com.slack.circuit.foundation.Circuit

internal class CircuitProviderImpl(summaryPresenter: SummaryPresenter) : CircuitProvider {
    override val circuit: Circuit = Circuit.Builder()
        .addPresenter<SummaryScreen, SummaryState>(summaryPresenter)
        .addUi<SummaryScreen, SummaryState> { state, modifier -> Summary(state, modifier) }
        .build()
}