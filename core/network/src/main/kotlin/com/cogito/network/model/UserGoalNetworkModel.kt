package com.cogito.network.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class UserGoalNetworkModel(
    @Json(name = "id")
    val userId: String,
    @Json(name = "goal_ml")
    val goalMilliliters: Int
)
