package com.cogito.data.repository.hydration

import android.util.Log
import com.cogito.data.model.asEntity
import com.cogito.data.model.asNetworkModel
import com.cogito.database.dao.HydrationDao
import com.cogito.model.data.HydrationDataModel
import com.cogito.network.datasource.hydration.HydrationNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

internal class HydrationRepositoryImpl(
    private val dao: HydrationDao,
    private val hydrationDataSource: HydrationNetworkDataSource
) : HydrationRepository {
    override fun getHydrationSummaryToday(): Flow<Int> {
        Log.d("HydrationRepositoryImpl", "getHydrationSummaryToday: ")
        val now = Clock.System.now()
        val todayStart = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.atStartOfDayIn(
            TimeZone.currentSystemDefault()
        )
        val duration = Duration.parse("PT23H59M59S")
        val todayEnd = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.atStartOfDayIn(
            TimeZone.currentSystemDefault()
        ).plus(duration)
        return dao.getHydrationSummary(todayStart, todayEnd)
    }

    override suspend fun addHydration(hydration: HydrationDataModel, userId: String) {
        Log.d("HydrationRepositoryImpl", "addHydration: Saving to database")
        dao.insertHydration(hydration.asEntity())
        Log.d("HydrationRepositoryImpl", "addHydration: Saving to network")
        hydrationDataSource.addHydration(hydration.asNetworkModel(userId))
        Log.d("HydrationRepositoryImpl", "addHydration: Updating sync state")
        dao.updateHydration(hydration.asEntity().copy(isSynced = true))
    }

    override suspend fun syncHydration(userId: String) {
        Log.d("HydrationRepositoryImpl", "syncHydration: Getting hydration list for syncing")
        val hydrationListForSyncing = dao.getHydrationListForSyncing()
        Log.d("HydrationRepositoryImpl", "syncHydration: Syncing hydration list")
        hydrationListForSyncing.forEach { hydration ->
            Log.d("HydrationRepositoryImpl", "syncHydration: Syncing hydration ${hydration.id}")
            hydrationDataSource.addHydration(hydration.asNetworkModel(userId))
            Log.d("HydrationRepositoryImpl", "syncHydration: Updating sync state")
            dao.updateHydration(hydration.copy(isSynced = true))
        }
    }
}