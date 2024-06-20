package com.cogito.hydration.data.repository

import com.cogito.hydration.data.di.SupabaseProvider
import com.cogito.hydration.data.model.DailyHydrationIntake
import com.cogito.hydration.data.model.HydrationIntake
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.postgresSingleDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.Flow

internal class HydrationRepositoryImpl(private val supabaseProvider: SupabaseProvider) :
    HydrationRepository {

    override suspend fun getHydrationIntake(userId: Int): Flow<List<HydrationIntake>> {
        val result = supabaseProvider.realtimeChannel.postgresListDataFlow(
            schema = "public",
            table = "hydration_intake",
            primaryKey = HydrationIntake::id
        )
        return result
    }

    override suspend fun getHydrationIntakeForTheDay(userId: Int): Flow<DailyHydrationIntake> {
        return supabaseProvider.realtimeChannel.postgresSingleDataFlow(
            schema = "public",
            table = "user_daily_hydration_intake",
            primaryKey = DailyHydrationIntake::userId,
        ) {
            DailyHydrationIntake::userId eq userId
        }
    }

    override suspend fun addHydrationIntake(intake: HydrationIntake) {
        supabaseProvider.client.from("hydration_intake").insert(intake)
    }

    override suspend fun subscribeToRealtimeChannel() {
        supabaseProvider.realtimeChannel.subscribe()
    }

    override suspend fun unsubscribeRealtimeChannel() {
        supabaseProvider.client.realtime.removeChannel(supabaseProvider.realtimeChannel)
    }
}