package com.ab21.data.datasource

import java.util.UUID

interface ICacheDatasource<T> {

    /**
     * This method provides a list of cache object by key
     *
     * @param key the unique key to reference the cached value
     * @return a list of cached objects
     */
    fun get(key: UUID): List<T>

    /**
     * This method write the list of elements into cache by key
     *
     * @param key the unique key to reference the cached value
     * @param value the list of elements to store
     */
    fun set(key: UUID, value: List<T>)

    /**
     * This method remove a cached value by key
     *
     * @param key the unique key to reference the cache value
     */
    fun remove(key: UUID)

    /**
     * This method drop all cached elements
     *
     */
    fun clear()
}
