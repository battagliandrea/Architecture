package com.ab21.domain.model

data class CachePolicy(
    val type: Type = Type.ALWAYS,
    val expires: Long = 0
){
    enum class Type {
        /**
         * Never create a cache line for the key
         */
        NEVER,

        /**
         * Always create a cache line for the key
         */
        ALWAYS,

        /**
         *  Re-fetch (refresh) the cache line for the key
         */
        REFRESH,

        /**
         * Clear the cache line for the key after request
         */
        CLEAR,

        /**
         * Expire this cache line and refresh if older than expires
         */
        EXPIRES
    }
}
