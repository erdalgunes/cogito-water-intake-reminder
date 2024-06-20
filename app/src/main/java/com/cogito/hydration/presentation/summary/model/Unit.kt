package com.cogito.hydration.presentation.summary.model

sealed class Unit(val milliliterMultiplier: Float) {
    data object Milliliters : Unit(1f)
    data object Liters : Unit(1000f)
}