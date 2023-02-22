package com.ab21.data.datasource

import arrow.core.Either
import com.ab21.core.errors.Error
import kotlinx.serialization.KSerializer

interface ICacheDatasource {

    /**
     * This method provides an either with the deserialized object
     *
     * @param key the unique key to reference the cached value
     * @param serializer the KSerializer to deserialize the generic object
     * @return an either with the deserialized object
     */
    suspend fun <T : Any> get(key: String, serializer: KSerializer<T>): Either<Error.DatabaseError, T>

    /**
     * This method serialize and write the object into cache and return a value with the result
     *
     * @param key the unique key to reference the cached value
     * @param serializer the KSerializer to serialize the generic object
     * @return an either with the result
     */
    suspend fun <T : Any> set(key: String, data: T, serializer: KSerializer<T>): Either<Error.DatabaseError, Boolean>

    /**
     * This method remove a cached value by key
     *
     * @param key the unique key to reference the cached value
     * @return an either with the result
     */
    suspend fun remove(key: String): Either<Error.DatabaseError, Boolean>

    /**
     * This method drop all cached elements
     * @return an either with the result
     */
    suspend fun clear(): Either<Error.DatabaseError, Boolean>
}
