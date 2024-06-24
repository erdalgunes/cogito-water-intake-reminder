package com.cogito.hydration.presentation.summary.state

import com.cogito.hydration.data.model.HydrationIntake
import com.slack.circuit.runtime.CircuitUiEvent

sealed interface SummaryEvent : CircuitUiEvent {
    data class AddHydration(val hydration: HydrationIntake) : SummaryEvent
}