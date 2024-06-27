package com.cogito.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import java.util.UUID

@Entity(
    tableName = "hydration",
)
data class HydrationEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("user_id")
    val userId: String,
    @ColumnInfo("amount_ml")
    val amount: Int,
    @ColumnInfo("intake_timestamp")
    val timestamp: Instant,
)