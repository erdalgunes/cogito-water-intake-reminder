package com.cogito.network.datasource.hydration

import com.cogito.network.model.HydrationNetworkModel
import com.cogito.network.supabase.SupabaseProvider
import io.github.jan.supabase.postgrest.from

class HydrationNetworkDataSourceImpl(
    private val supabaseProvider: SupabaseProvider,
): HydrationNetworkDataSource {
    override suspend fun addHydration(hydration: HydrationNetworkModel) {
        supabaseProvider.client.from("hydration").insert(hydration)
    }
}