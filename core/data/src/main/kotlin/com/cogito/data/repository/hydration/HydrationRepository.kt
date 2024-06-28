package com.cogito.data.repository.hydration

import com.cogito.model.data.HydrationDataModel
import kotlinx.coroutines.flow.Flow

interface HydrationRepository {
    /**
     * Returns the total amount of water consumed today in milliliters
     */
    fun getHydrationSummaryToday(): Flow<Int>

    /**
     * Adds a new hydration intake to the database
     */
    suspend fun addHydration(hydration: HydrationDataModel, userId: String)

    /**
     * Syncs the hydration data with the server
     */
    suspend fun syncHydration(userId: String)
}