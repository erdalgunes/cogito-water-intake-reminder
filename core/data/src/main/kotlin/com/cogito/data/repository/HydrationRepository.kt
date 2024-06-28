package com.cogito.data.repository

import com.cogito.model.data.HydrationDataModel
import com.cogito.model.ui.HydrationSummary
import kotlinx.coroutines.flow.Flow

interface HydrationRepository {
    fun getHydrationSummaryToday(userId: String): Flow<Int>
    suspend fun addHydrationIntake(userId: String, intake: HydrationDataModel)
}