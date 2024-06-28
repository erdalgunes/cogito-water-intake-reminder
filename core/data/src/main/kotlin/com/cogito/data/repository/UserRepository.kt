package com.cogito.data.repository

import com.cogito.model.data.UserDataModel

interface UserRepository {

    suspend fun upsertUser(user: UserDataModel)

    suspend fun getUserId(email: String): String
}