package com.cogito.model.ui

data class HydrationSummary(
    val hydrationToday: Int,
    val hydrationGoal: Int = 2500,
)
