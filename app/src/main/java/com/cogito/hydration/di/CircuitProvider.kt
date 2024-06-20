package com.cogito.hydration.di

import com.slack.circuit.foundation.Circuit

interface CircuitProvider {
    val circuit: Circuit
}