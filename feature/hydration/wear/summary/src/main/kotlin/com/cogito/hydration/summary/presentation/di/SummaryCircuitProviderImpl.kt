package com.cogito.hydration.summary.presentation.di

import com.cogito.hydration.summary.presentation.Summary
import com.cogito.hydration.summary.presentation.SummaryPresenter
import com.cogito.hydration.summary.presentation.state.SummaryScreen
import com.cogito.hydration.summary.presentation.state.SummaryState
import com.slack.circuit.foundation.Circuit

internal class SummaryCircuitProviderImpl(summaryPresenter: SummaryPresenter) : SummaryCircuitProvider {
    override val circuit: Circuit = Circuit.Builder()
        .addPresenter<SummaryScreen, SummaryState>(summaryPresenter)
        .addUi<SummaryScreen, SummaryState> { state, modifier -> Summary(state, modifier) }
        .build()
}