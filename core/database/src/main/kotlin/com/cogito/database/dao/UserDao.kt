package com.cogito.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.cogito.database.model.UserEntity

@Dao
interface UserDao {
    @Upsert
    fun upsertUser(user: UserEntity)

    @Query("SELECT id FROM user_info WHERE user_email = :email")
    fun getUserId(email: String): String
}