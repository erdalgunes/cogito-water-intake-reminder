package com.cogito.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.cogito.database.model.HydrationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

@Dao
interface HydrationDao {

    @Query("SELECT * FROM hydration")
    suspend fun getHydrationList(): List<HydrationEntity>

    @Transaction
    @Query(
        value = """
            SELECT SUM(hydration.amount_ml) AS hydrationToday 
            FROM hydration
            WHERE hydration.created_at BETWEEN :startOfDay AND :endOfDay
        """,
    )
    fun getHydrationSummary(startOfDay: Instant, endOfDay: Instant): Flow<Int>

    @Insert
    suspend fun insertHydration(hydration: HydrationEntity)

    @Update
    suspend fun updateHydration(hydration: HydrationEntity)

    @Query("SELECT * FROM hydration WHERE is_synced = 0")
    suspend fun getHydrationListForSyncing(): List<HydrationEntity>
}