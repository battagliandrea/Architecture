package com.ab21.data.repository

import com.ab21.data.datasource.ICacheDatasource
import com.ab21.data.datasource.INetworkDatasource
import com.ab21.domain.model.CachePolicy
import com.ab21.domain.model.DomainResult
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import com.ab21.domain.repository.IPokemonRepository
import javax.inject.Inject


internal class PokemonRepository @Inject constructor(
    private val networkDatasource: INetworkDatasource,
    private val cache: ICacheDatasource,
) : IPokemonRepository {
    override suspend fun getPokemon(
        cachePolicy: CachePolicy,
        nextPage: Boolean,
        limit: Int
    ): DomainResult<PagingSource<Pokemon>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(cachePolicy: CachePolicy, id: Int): DomainResult<Pokemon> {
        TODO("Not yet implemented")
    }
}