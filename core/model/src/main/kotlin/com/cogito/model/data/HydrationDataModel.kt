package com.cogito.model.data

import kotlinx.datetime.Instant

data class HydrationDataModel(
    val amount: Int,
    val timestamp: Instant,
)
