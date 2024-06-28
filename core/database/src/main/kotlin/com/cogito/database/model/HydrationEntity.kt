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
    @ColumnInfo("amount_ml")
    val amount: Int,
    @ColumnInfo("created_at")
    val createdAt: Instant,
    @ColumnInfo("is_synced")
    val isSynced: Boolean = false,
)