package com.cogito.data.repository

import com.cogito.data.model.asEntity
import com.cogito.database.dao.HydrationDao
import com.cogito.model.data.HydrationDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

internal class HydrationRepositoryImpl(
    private val dao: HydrationDao,
) : HydrationRepository {
    override fun getHydrationSummaryToday(userId: String): Flow<Int> {
        val now = Clock.System.now()
        val todayStart = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.atStartOfDayIn(
            TimeZone.currentSystemDefault())
        val duration = Duration.parse("PT23H59M59S")
        val todayEnd = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.atStartOfDayIn(
            TimeZone.currentSystemDefault()).plus(duration)
        return dao.getHydrationSummary(userId, todayStart, todayEnd)
    }

    override suspend fun addHydrationIntake(userId: String, intake: HydrationDataModel) =
        dao.upsertHydration(intake.asEntity(userId))
}