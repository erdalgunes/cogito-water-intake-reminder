package com.cogito.water.data.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class WaterIntake(
    val id: Int,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "amount_ml")
    val amount: Int,
    @Json(name = "intake_timestamp")
    val timestamp: String,
    val notes: String,
)
