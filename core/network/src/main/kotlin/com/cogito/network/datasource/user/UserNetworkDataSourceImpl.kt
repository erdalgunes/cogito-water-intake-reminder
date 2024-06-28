package com.cogito.network.datasource.user

import android.util.Log
import com.cogito.network.model.UserGoalNetworkModel
import com.cogito.network.supabase.SupabaseProvider
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow

internal class UserNetworkDataSourceImpl(
    private val supabaseProvider: SupabaseProvider,
) : UserNetworkDataSource {
    override suspend fun authenticateUser() {
        Log.d("UserNetworkDataSourceImpl", "authenticateUser: Signing in...")
        supabaseProvider.client.auth.signInWith(Email) {
            email = "erdalgns@gmail.com"
            password = "350416"
        }
        Log.d("UserNetworkDataSourceImpl", "authenticateUser: Signed in!")
    }

    override fun observeUserAuth(): Flow<SessionStatus> {
        return supabaseProvider.client.auth.sessionStatus
    }

    override suspend fun getAuthenticatedUserId(): String {
        Log.d("UserNetworkDataSourceImpl", "getAuthenticatedUserId: Retrieving user info...")
        val userInfo =
            supabaseProvider.client.auth.retrieveUserForCurrentSession(updateSession = true)
        Log.d("UserNetworkDataSourceImpl", "getAuthenticatedUserId: $userInfo")
        return userInfo.id
    }

    override suspend fun syncUserHydrationGoal(userId: String, goal: Int) {
        supabaseProvider.client.from("user_goals").insert(UserGoalNetworkModel(userId, goal))
    }
}