package com.cogito.network.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class HydrationNetworkModel(
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "amount_ml")
    val amountMilliliters: Int,
    @Json(name = "created_at")
    val createdAt: String,
)
