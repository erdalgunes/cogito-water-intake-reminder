package com.cogito.hydration.summary.presentation.state

import androidx.compose.runtime.Immutable
import com.cogito.model.data.HydrationDataModel
import com.slack.circuit.runtime.CircuitUiEvent

@Immutable
sealed interface SummaryEvent : CircuitUiEvent {
    data class AddHydration(val hydration: HydrationDataModel) : SummaryEvent
}