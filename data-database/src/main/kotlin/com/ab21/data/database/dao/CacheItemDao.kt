package com.ab21.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab21.data.database.model.CacheItem

@Dao
interface CacheItemDao {

    @Query("SELECT * FROM cache_items")
    suspend fun findAll(): List<CacheItem>

    @Query("SELECT * FROM cache_items WHERE `key`=:key LIMIT 1")
    suspend fun findByKey(key: String): CacheItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CacheItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bulkInsert(items: List<CacheItem>): List<Long>

    @Query("DELETE FROM cache_items WHERE `key`=:key")
    suspend fun deleteById(key: String): Int

    @Query("DELETE FROM cache_items")
    suspend fun clear(): Int
}