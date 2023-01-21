package com.ab21.data.network.model

import com.ab21.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)

/**
 * Map from DTO to domain model
 */
fun PokemonDTO?.toDomain(imageUrl: String): Pokemon {
    val id = this?.url?.substring(0, url.length - 1)?.split("/")?.last()?.toInt() ?: 0
    return Pokemon(
        id = id,
        name = this?.name.orEmpty(),
        image = id?.let { "${imageUrl}/$id.png" }.orEmpty()
    )
}

/**
 * Map DTO model list
 */
fun List<PokemonDTO?>.toDomain(imageUrl: String): List<Pokemon> =
    this.map { it.toDomain(imageUrl) }