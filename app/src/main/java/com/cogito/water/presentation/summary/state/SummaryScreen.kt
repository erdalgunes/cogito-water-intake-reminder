package com.cogito.water.presentation.summary.state

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SummaryScreen : Screen {
    data class State(
        val isError: Boolean,
        val waterGoal: Int?= null,
        val waterIntake: Int? = null,
    ): CircuitUiState
}