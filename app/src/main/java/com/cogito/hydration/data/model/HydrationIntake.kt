package com.cogito.hydration.data.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class HydrationIntake(
    val id: Int? = null,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "amount_ml")
    val amount: Int,
    @Json(name = "intake_timestamp")
    val timestamp: String? = null,
    val notes: String? = null,
)
