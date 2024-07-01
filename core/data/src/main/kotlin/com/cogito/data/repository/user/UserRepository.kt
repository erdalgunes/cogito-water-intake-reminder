package com.cogito.data.repository.user

interface UserRepository {

    suspend fun authenticateUser(): String
    /**
     * @return the user goal in milliliters
     */
    suspend fun getUserHydrationGoal(): Int

    suspend fun syncUserHydrationGoal(userId: String, goal: Int)

    suspend fun getUserId(): String
}