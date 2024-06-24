package com.cogito.hydration.presentation.summary.state

import com.cogito.hydration.presentation.summary.model.Drink
import com.slack.circuit.runtime.CircuitUiState

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