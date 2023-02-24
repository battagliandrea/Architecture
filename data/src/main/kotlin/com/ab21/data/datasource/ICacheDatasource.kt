package com.ab21.data.datasource

import com.ab21.core.model.Result
import kotlinx.serialization.KSerializer

interface ICacheDatasource {

    /**
     * This method provides an either with the deserialized object
     *
     * @param key the unique key to reference the cached value
     * @param serializer the KSerializer to deserialize the generic object
     * @return an either with the deserialized object
     */
    suspend fun <T : Any> get(key: String, serializer: KSerializer<T>): Result<T>

    /**
     * This method serialize and write the object into cache and return a value with the result
     *
     * @param key the unique key to reference the cached value
     * @param serializer the KSerializer to serialize the generic object
     * @return an either with the result
     */
    suspend fun <T : Any> set(key: String, data: T, serializer: KSerializer<T>): Result<Boolean>

    /**
     * This method remove a cached value by key
     *
     * @param key the unique key to reference the cached value
     * @return an either with the result
     */
    suspend fun remove(key: String): Result<Boolean>

    /**
     * This method drop all cached elements
     * @return an either with the result
     */
    suspend fun clear(): Result<Boolean>
}
