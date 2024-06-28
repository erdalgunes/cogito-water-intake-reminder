package com.cogito.model.data

import kotlinx.datetime.Instant

data class HydrationDataModel(
    val amountMilliliters: Int,
    val createdAt: Instant,
)
