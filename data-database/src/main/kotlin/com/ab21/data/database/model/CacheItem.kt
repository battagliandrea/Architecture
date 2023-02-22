package com.ab21.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.concurrent.TimeUnit

@kotlinx.serialization.Serializable
@Entity(tableName = "cache_items")
data class CacheItem constructor(
    @PrimaryKey val key: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "ttl") val ttl: Long
){
    companion object {
        fun getDailyTTl(): Long = Date().time + TimeUnit.HOURS.toMillis(24)
        fun getShortTTl(): Long = Date().time + TimeUnit.HOURS.toMillis(1)
    }
}
