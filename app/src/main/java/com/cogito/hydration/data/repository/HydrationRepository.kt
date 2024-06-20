package com.cogito.hydration.data.repository

import com.cogito.hydration.data.model.DailyHydrationIntake
import com.cogito.hydration.data.model.HydrationIntake
import kotlinx.coroutines.flow.Flow

interface HydrationRepository {
    suspend fun getHydrationIntake(userId: Int): Flow<List<HydrationIntake>>
    suspend fun getHydrationIntakeForTheDay(userId: Int): Flow<DailyHydrationIntake>
    suspend fun subscribeToRealtimeChannel()
    suspend fun unsubscribeRealtimeChannel()
    suspend fun addHydrationIntake(intake: HydrationIntake)
}