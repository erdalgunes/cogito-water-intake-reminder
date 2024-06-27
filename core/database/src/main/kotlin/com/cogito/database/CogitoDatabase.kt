package com.cogito.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cogito.database.dao.HydrationDao
import com.cogito.database.dao.UserDao
import com.cogito.database.model.HydrationEntity
import com.cogito.database.model.UserEntity
import com.cogito.database.util.InstantConverter

@Database(
    entities = [
        HydrationEntity::class,
        UserEntity::class
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class CogitoDatabase : RoomDatabase() {
    abstract fun hydrationDao(): HydrationDao
    abstract fun userDao(): UserDao
}