package com.ab21.data.repository

import com.ab21.core.model.Result
import com.ab21.domain.CachePolicy
import com.ab21.data.datasource.ICacheDatasource
import com.ab21.data.datasource.INetworkDatasource
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import com.ab21.domain.repository.IPokemonRepository
import javax.inject.Inject

internal class PokemonRepository @Inject constructor(
    private val networkDatasource: INetworkDatasource,
    private val cacheDatasource: ICacheDatasource,
) : IPokemonRepository {

    companion object {
        const val REPOSITORY_ID: String = "PokemonRepository"
    }

    private val offset = 0
    private val pagingSerializer by lazy { PagingSource.serializer(Pokemon.serializer()) }

    override suspend fun getPokemon(
        cachePolicy: CachePolicy,
        nextPage: Boolean,
        limit: Int
    ): Result<PagingSource<Pokemon>> =
        networkDatasource.pokemon(limit= limit, offset = 0)

    override suspend fun getPokemon(
        cachePolicy: CachePolicy,
        id: Int
    ): Result<Pokemon> = networkDatasource.pokemon(id)
}
