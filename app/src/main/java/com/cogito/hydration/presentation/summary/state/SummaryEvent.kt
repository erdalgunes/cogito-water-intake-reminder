package com.cogito.hydration.presentation.summary.state

import androidx.compose.runtime.Immutable
import com.cogito.model.data.HydrationDataModel
import com.slack.circuit.runtime.CircuitUiEvent

@Immutable
sealed interface SummaryEvent : CircuitUiEvent {
    data class AddHydration(val hydration: HydrationDataModel) : SummaryEvent
}