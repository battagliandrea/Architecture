package com.ab21.domain.repository

import com.ab21.domain.model.CachePolicy
import com.ab21.domain.model.DomainResult
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    /**
     * This method returns a paginated list of pokemon objects
     * and it is possible to cache the results on local database
     * in order to reduce network traffic and improve performance.
     *
     * @param cachePolicy the cache policy setting
     * @param nextPage to request next available page. Doesn't work if cachePolicy has been set NEVER or REFRESH.
     * @param limit Maximum limit of available object per page. Default 50
     * @return a list of pokemon objects
     * @see Pokemon
     * @see CachePolicy
     */
    suspend fun getPokemon(cachePolicy: CachePolicy, nextPage: Boolean, limit: Int = 50): DomainResult<PagingSource<Pokemon>>

    /**
     * This method returns a detail of pokemon objects
     * and it is possible to cache the results on local database
     * in order to reduce network traffic and improve performance.
     *
     * @param cachePolicy the cache policy setting
     * @param id The pokemon unique identifier
     * @return a pokemon object
     * @see Pokemon
     * @see CachePolicy
     */
    suspend fun getPokemon(cachePolicy: CachePolicy, id: Int): DomainResult<Pokemon>
}