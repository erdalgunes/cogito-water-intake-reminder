package com.cogito.hydration.data.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class DailyHydrationIntake(
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "total_intake")
    val totalIntake: Int,
    @Json(name = "user_goal")
    val userGoal: Int,
)
