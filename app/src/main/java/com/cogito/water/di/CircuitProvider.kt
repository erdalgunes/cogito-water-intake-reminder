package com.cogito.water.di

import com.slack.circuit.foundation.Circuit

interface CircuitProvider {
    val circuit: Circuit
}