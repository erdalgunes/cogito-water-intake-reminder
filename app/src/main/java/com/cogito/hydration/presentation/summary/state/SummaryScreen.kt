package com.cogito.hydration.presentation.summary.state

import com.cogito.hydration.presentation.summary.model.Drink
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SummaryScreen : Screen {
    data class State(
        val isError: Boolean = false,
        val isLoading: Boolean = false,
        val hydrationGoal: Int? = null,
        val hydrationToday: Int? = null,
        val drinks: List<Drink> = listOf(
            Drink.GlassOfWater,
            Drink.CupOfCoffee,
        )
    ) : CircuitUiState
}