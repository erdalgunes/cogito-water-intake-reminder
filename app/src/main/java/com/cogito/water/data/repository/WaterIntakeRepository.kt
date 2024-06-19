package com.cogito.water.data.repository

import com.cogito.water.data.model.WaterIntake
import kotlinx.coroutines.flow.Flow

interface WaterIntakeRepository {
    suspend fun getWaterIntake(userId: Int): Flow<List<WaterIntake>>
}