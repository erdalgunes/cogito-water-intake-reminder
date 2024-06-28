package com.cogito.network.datasource.hydration

import com.cogito.network.model.HydrationNetworkModel

interface HydrationNetworkDataSource {
    suspend fun addHydration(hydration: HydrationNetworkModel)
}