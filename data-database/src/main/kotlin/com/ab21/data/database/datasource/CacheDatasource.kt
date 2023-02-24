package com.ab21.data.database.datasource

import com.ab21.data.database.dao.CacheDatabase
import com.ab21.data.database.model.CacheItem
import com.ab21.data.datasource.ICacheDatasource
import com.ab21.domain.model.Result
import com.ab21.domain.model.leftResult
import com.ab21.domain.model.rightResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import com.ab21.domain.model.AppError.Cache

class CacheDatasource @Inject constructor(
    private val database: CacheDatabase,
) : ICacheDatasource {

    override suspend fun <T : Any> get(key: String, serializer: KSerializer<T>): Result<T> =
        try {
            database.cacheItemDao().findByKey(key)
                .let { query -> Json.decodeFromString(serializer, query.data) }
                .rightResult()
        } catch (e: Exception){
            Cache.ReadingError(e).leftResult()
        }

    override suspend fun <T : Any> set(key: String, data: T, serializer: KSerializer<T>): Result<Boolean> =
        try {
            CacheItem(key = key, data= Json.encodeToString(serializer, data), ttl = CacheItem.getShortTTl())
                .let{ cacheItem -> database.cacheItemDao().insert(cacheItem) }
                .let { result -> result > 0 }
                .rightResult()
        } catch (e: Exception){
            Cache.WritingError(e).leftResult()
        }

    override suspend fun remove(key: String): Result<Boolean> =
        try {
            database.cacheItemDao().deleteById(key)
                .let{ query -> query > 0}
                .rightResult()
        } catch (e: Exception){
            Cache.DeletingError(e).leftResult()
        }

    override suspend fun clear(): Result<Boolean> =
        try {
            database.cacheItemDao().clear()
                .let{ query -> query > 0}
                .rightResult()
        } catch (e: Exception){
            Cache.DeletingError(e).leftResult()
        }
}
