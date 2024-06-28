package com.cogito.hydration.summary.presentation.state

import androidx.compose.runtime.Stable
import com.cogito.model.ui.Drink
import com.slack.circuit.runtime.CircuitUiState

@Stable
data class SummaryState(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val hydrationGoal: Int? = null,
    val hydrationToday: Int? = null,
    val drinks: List<Drink> = listOf(
        Drink.GlassOfWater,
        Drink.CupOfCoffee,
    ),
    val loadingAmount: Int? = null,
    val eventSink: (SummaryEvent) -> Unit,
) : CircuitUiState