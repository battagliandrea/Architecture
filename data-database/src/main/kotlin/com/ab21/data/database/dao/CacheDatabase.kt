package com.ab21.data.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ab21.data.database.model.CacheItem

@Database(
    entities = [
        CacheItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CacheDatabase : RoomDatabase() {

    abstract fun cacheItemDao(): CacheItemDao

}