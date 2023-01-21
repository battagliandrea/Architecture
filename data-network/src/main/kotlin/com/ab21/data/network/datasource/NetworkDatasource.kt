package com.ab21.data.network.datasource

import arrow.core.Either
import com.ab21.data.datasource.INetworkDatasource
import com.ab21.data.network.env.Environment
import com.ab21.data.network.extensions.enqueueResponse
import com.ab21.data.network.model.toDomain
import com.ab21.data.network.service.PokeApi
import com.ab21.core.errors.Error.NetworkError
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import javax.inject.Inject

internal class NetworkDatasource @Inject constructor(
    private val environment: Environment,
    private val pokeApi: PokeApi
) : INetworkDatasource {

    override suspend fun pokemon(
        limit: Int,
        offset: Int
    ): Either<NetworkError, PagingSource<Pokemon>> =
        enqueueResponse(
            call = { pokeApi.pokemon(limit = limit, offset = offset) },
            mapper = { body -> body.toDomain(environment.imagesBaseUrl) }
        )

    override suspend fun pokemon(id: Int): Either<NetworkError, Pokemon> =
        enqueueResponse(
            call = { pokeApi.pokemon(id= id) },
            mapper = { body -> body.toDomain(environment.imagesBaseUrl) }
        )
}
