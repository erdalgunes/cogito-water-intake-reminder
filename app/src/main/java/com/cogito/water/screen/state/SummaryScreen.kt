package com.cogito.water.screen.state

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object SummaryScreen : Screen{
    data class State(
        val waterIntake: Int,
        val waterGoal: Int
    ): CircuitUiState
}