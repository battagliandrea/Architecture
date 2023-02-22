package com.ab21.data.database.datasource

import arrow.core.Either
import com.ab21.core.errors.Error.DatabaseError
import com.ab21.data.database.dao.CacheDatabase
import com.ab21.data.database.model.CacheItem
import com.ab21.data.datasource.ICacheDatasource
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CacheDatasource @Inject constructor(
    private val database: CacheDatabase,
) : ICacheDatasource {

    override suspend fun <T : Any> get(key: String, serializer: KSerializer<T>): Either<DatabaseError, T> =
        try {
            val query = database.cacheItemDao().findByKey(key)
            val data = Json.decodeFromString(serializer, query.data)
            Either.Right(data)
        } catch (e: Exception){
            e.printStackTrace()
            Either.Left(DatabaseError.ReadingError)
        }

    override suspend fun <T : Any> set(key: String, data: T, serializer: KSerializer<T>): Either<DatabaseError, Boolean> =
        try {
            val json = Json.encodeToString(serializer, data)
            set(key, json)
        } catch (e: Exception){
            e.printStackTrace()
            Either.Left(DatabaseError.WritingError)
        }

    private suspend fun set(key: String, json: String) = run {
        val cacheItem = CacheItem(key = key, data= json, ttl = CacheItem.getShortTTl())
        val result = database.cacheItemDao().insert(cacheItem)
        Either.Right(result > 0)
    }

    override suspend fun remove(key: String): Either<DatabaseError, Boolean> =
        try {
            val query = database.cacheItemDao().deleteById(key)
            Either.Right(query > 0)
        } catch (e: Exception){
            e.printStackTrace()
            Either.Left(DatabaseError.DeletingError)
        }

    override suspend fun clear(): Either<DatabaseError, Boolean> =
        try {
            val query = database.cacheItemDao().clear()
            Either.Right(query > 0)
        } catch (e: Exception){
            e.printStackTrace()
            Either.Left(DatabaseError.DeletingError)
        }
}
