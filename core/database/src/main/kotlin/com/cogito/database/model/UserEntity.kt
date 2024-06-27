package com.cogito.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "user_info"
)
data class UserEntity(
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo("user_name")
    val name: String,
    @PrimaryKey
    @ColumnInfo("user_email")
    val email: String,
    @ColumnInfo("user_goal")
    val goal: Int,
)
