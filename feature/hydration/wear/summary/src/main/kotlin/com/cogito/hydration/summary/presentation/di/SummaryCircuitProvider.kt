package com.cogito.hydration.summary.presentation.di

import com.slack.circuit.foundation.Circuit

interface SummaryCircuitProvider {
    val circuit: Circuit
}