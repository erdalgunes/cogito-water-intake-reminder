package com.cogito.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.cogito.database.model.HydrationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

@Dao
interface HydrationDao {

    @Query("SELECT * FROM hydration")
    fun getHydrationList(): List<HydrationEntity>

    @Transaction
    @Query(
        value = """
            SELECT SUM(hydration.amount_ml) AS hydrationToday 
            FROM hydration 
            WHERE hydration.user_id = :userId 
            AND hydration.intake_timestamp BETWEEN :startOfDay AND :endOfDay
        """,
    )
    fun getHydrationSummary(userId: String, startOfDay: Instant, endOfDay: Instant): Flow<Int>

    @Upsert
    fun upsertHydration(hydration: HydrationEntity)

    @Upsert
    fun upsertHydrationList(hydrationList: List<HydrationEntity>)

    @Query("DELETE FROM hydration WHERE id = :hydrationId")
    fun deleteHydration(hydrationId: Int)

    @Query("DELETE FROM hydration WHERE id in (:hydrationIds)")
    fun deleteHydrationList(hydrationIds: List<Int>)
}