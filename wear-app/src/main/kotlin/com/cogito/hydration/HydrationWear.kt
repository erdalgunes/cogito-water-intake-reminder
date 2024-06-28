package com.cogito.hydration

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cogito.hydration.summary.presentation.di.SummaryCircuitProvider
import com.cogito.hydration.summary.presentation.state.SummaryScreen
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HydrationWear(circuitProvider: SummaryCircuitProvider = koinInject()) {
    KoinAndroidContext {
        CircuitCompositionLocals(circuitProvider.circuit) {
            CircuitContent(SummaryScreen, modifier = Modifier.fillMaxSize())
        }
    }
}