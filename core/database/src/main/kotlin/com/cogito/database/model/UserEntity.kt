package com.cogito.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "user_info"
)
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "goal_ml")
    val goalMilliliters: Int = 2500
)
