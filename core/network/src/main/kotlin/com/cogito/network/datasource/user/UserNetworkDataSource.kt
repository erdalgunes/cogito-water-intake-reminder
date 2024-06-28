package com.cogito.network.datasource.user

import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.Flow

interface UserNetworkDataSource {
    suspend fun authenticateUser()

    fun observeUserAuth(): Flow<SessionStatus>

    suspend fun getAuthenticatedUserId() : String

    suspend fun syncUserHydrationGoal(userId: String, goal: Int)
}