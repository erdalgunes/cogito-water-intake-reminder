package com.cogito.network.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    val id: String,
)
