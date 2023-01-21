package com.ab21.data.network.model


import com.ab21.domain.model.PagingSource
import com.ab21.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationEntity<out T>(
    @SerialName("count")
    val count: Long?,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<T?>?
)

/**
 * Map from DTO to domain model
 */
fun PaginationEntity<PokemonDTO>?.toDomain(imageUrl: String) =
    PagingSource(
        count = this?.count ?: 0,
        hasNext = this?.next != null,
        data = this?.results?.map{ it.toDomain(imageUrl) }.orEmpty()
    )