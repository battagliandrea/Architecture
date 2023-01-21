package com.ab21.data.network.service

import com.ab21.data.network.model.PaginationEntity
import com.ab21.data.network.model.PokemonDTO
import com.ab21.data.network.model.PokemonDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("v2/pokemon/")
    suspend fun pokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PaginationEntity<PokemonDTO>>

    @GET("v2/pokemon/{id}")
    suspend fun pokemon(@Path("id") id: Int): Response<PokemonDetailsDTO>
}
