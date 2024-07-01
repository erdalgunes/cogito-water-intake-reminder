package com.cogito.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cogito.database.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT goal_ml FROM user_info")
    suspend fun getUserGoalInMilliliters(): Int

    @Query("SELECT id FROM user_info")
    suspend fun getUserId(): String
}