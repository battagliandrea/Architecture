package com.ab21.data.repository

import com.ab21.data.cache.CachePolicy
import com.ab21.data.datasource.ICacheDatasource
import com.ab21.data.datasource.INetworkDatasource
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import com.ab21.domain.repository.IPokemonRepository
import javax.inject.Inject

internal class PokemonRepository @Inject constructor(
    private val networkDatasource: INetworkDatasource,
    private val cache: ICacheDatasource,
) : IPokemonRepository {

    companion object {
        const val REPOSITORY_ID: String = "PokemonRepository"
        const val LIMIT: Int = 50
    }

    override suspend fun getPokemon(
        cachePolicy: CachePolicy,
        nextPage: Boolean,
        limit: Int
    ): Result<PagingSource<Pokemon>> {
        /*when(cachePolicy.type){
            NEVER -> {
                networkDatasource.pokemon(limit= LIMIT, offset = 0)
            }
            ALWAYS -> {
                networkDatasource.pokemon(limit = LIMIT, offset = 0)
                    .map { page ->
                        cache.set(key = REPOSITORY_ID + LIMIT, data = page, clazz = PagingSource::class.java)
                        return@map page
                    }
            }
            REFRESH -> {

            }
            CLEAR -> {

            }
            EXPIRES -> {

            }
        }*/
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(cachePolicy: CachePolicy, id: Int): Result<Pokemon> {
        TODO("Not yet implemented")
    }
}
