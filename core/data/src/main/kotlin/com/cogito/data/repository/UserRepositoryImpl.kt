package com.cogito.data.repository

import com.cogito.data.model.asEntity
import com.cogito.database.dao.UserDao
import com.cogito.model.data.UserDataModel

internal class UserRepositoryImpl(
    private val dao: UserDao
): UserRepository {
    override suspend fun upsertUser(user: UserDataModel) = dao.upsertUser(user.asEntity())

    override suspend fun getUserId(email: String): String = dao.getUserId(email)
}