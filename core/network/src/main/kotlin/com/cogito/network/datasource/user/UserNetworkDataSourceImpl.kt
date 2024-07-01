package com.cogito.network.datasource.user

import co.touchlab.kermit.Logger
import com.cogito.network.model.UserGoalNetworkModel
import com.cogito.network.supabase.SupabaseProvider
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow

internal class UserNetworkDataSourceImpl(
    private val supabaseProvider: SupabaseProvider,
    private val log: Logger,
) : UserNetworkDataSource {
    override suspend fun authenticateUser() {
        log.d("authenticateUser: Signing in...")
        supabaseProvider.client.auth.signInWith(Email) {
            email = "erdalgns@gmail.com"
            password = "350416"
        }
        log.d("authenticateUser: Signed in!")
    }

    override fun observeUserAuth(): Flow<SessionStatus> {
        return supabaseProvider.client.auth.sessionStatus
    }

    override suspend fun getAuthenticatedUserId(): String {
        log.d("getAuthenticatedUserId: Retrieving user info...")
        val userInfo =
            supabaseProvider.client.auth.retrieveUserForCurrentSession(updateSession = true)
        log.d("getAuthenticatedUserId: $userInfo")
        return userInfo.id
    }

    override suspend fun syncUserHydrationGoal(userId: String, goal: Int) {
        supabaseProvider.client.from("user_goals").upsert(UserGoalNetworkModel(userId, goal))
    }
}