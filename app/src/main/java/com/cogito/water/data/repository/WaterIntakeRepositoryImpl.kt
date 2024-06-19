package com.cogito.water.data.repository

import com.cogito.water.data.di.SupabaseProvider
import com.cogito.water.data.model.WaterIntake
import io.github.jan.supabase.realtime.postgresListDataFlow
import kotlinx.coroutines.flow.Flow

internal class WaterIntakeRepositoryImpl(private val supabaseProvider: SupabaseProvider): WaterIntakeRepository{

    override suspend fun getWaterIntake(userId: Int): Flow<List<WaterIntake>> {
        val result = supabaseProvider.realtimeChannel.postgresListDataFlow(schema = "public", table = "water_intake", primaryKey = WaterIntake::id)
        supabaseProvider.realtimeChannel.subscribe()
        return result
    }
}