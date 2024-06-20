package com.cogito.hydration.di

import com.cogito.hydration.data.repository.HydrationRepository
import com.cogito.hydration.presentation.summary.Summary
import com.cogito.hydration.presentation.summary.SummaryPresenter
import com.cogito.hydration.presentation.summary.state.SummaryScreen
import com.slack.circuit.foundation.Circuit

internal class CircuitProviderImpl(summaryPresenter: SummaryPresenter, hydrationRepository: HydrationRepository ) : CircuitProvider {
    override val circuit: Circuit = Circuit.Builder()
        .addPresenter<SummaryScreen, SummaryScreen.State>(summaryPresenter)
        .addUi<SummaryScreen, SummaryScreen.State> { state, modifier -> Summary(state, modifier, hydrationRepository) }
        .build()
}