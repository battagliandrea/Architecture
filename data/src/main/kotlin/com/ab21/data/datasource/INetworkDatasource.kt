package com.ab21.data.datasource

import arrow.core.Either
import com.ab21.core.errors.Error
import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon

interface INetworkDatasource {

    /**
     * This method returns a paginated list of pokemon objects from network
     *
     * @param offset how many elements to skips before beginning to return the pokemon.
     * @param limit maximum limit of available object per page. Default 50
     * @return a list of pokemon objects
     */
    suspend fun pokemon(limit: Int, offset: Int): Either<Error.NetworkError, PagingSource<Pokemon>>

    /**
     * This method returns a detail of pokemon objects
     *
     * @param id The pokemon unique identifier
     * @return a pokemon object
     */
    suspend fun pokemon(id: Int): Either<Error.NetworkError, Pokemon>
}
