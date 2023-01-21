package com.ab21.data.network.model

import com.ab21.data.network.BuildConfig
import com.ab21.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsDTO(
    @SerialName("abilities")
    val abilities: List<Ability?>?,
    @SerialName("base_experience")
    val baseExperience: Int?,
    @SerialName("forms")
    val forms: List<Form?>?,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice?>?,
    @SerialName("height")
    val height: Long?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_default")
    val isDefault: Boolean?,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String?,
    @SerialName("moves")
    val moves: List<Move?>?,
    @SerialName("name")
    val name: String?,
    @SerialName("order")
    val order: Int?,
    @SerialName("species")
    val species: Species?,
    @SerialName("stats")
    val stats: List<Stat?>?,
    @SerialName("types")
    val types: List<Type?>?,
    @SerialName("weight")
    val weight: Long?
) {
    @Serializable
    data class Ability(
        @SerialName("ability")
        val ability: AbilityData?,
        @SerialName("is_hidden")
        val isHidden: Boolean?,
        @SerialName("slot")
        val slot: Int?
    ) {
        @Serializable
        data class AbilityData(
            @SerialName("name")
            val name: String?,
            @SerialName("url")
            val url: String?
        )
    }

    @Serializable
    data class Form(
        @SerialName("name")
        val name: String?,
        @SerialName("url")
        val url: String?
    )

    @Serializable
    data class GameIndice(
        @SerialName("game_index")
        val gameIndex: Int?,
        @SerialName("version")
        val version: Version?
    ) {
        @Serializable
        data class Version(
            @SerialName("name")
            val name: String?,
            @SerialName("url")
            val url: String?
        )
    }

    @Serializable
    data class Move(
        @SerialName("move")
        val move: MoveData?,
        @SerialName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail?>?
    ) {
        @Serializable
        data class MoveData(
            @SerialName("name")
            val name: String?,
            @SerialName("url")
            val url: String?
        )

        @Serializable
        data class VersionGroupDetail(
            @SerialName("level_learned_at")
            val levelLearnedAt: Int?,
            @SerialName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod?,
            @SerialName("version_group")
            val versionGroup: VersionGroup?
        ) {
            @Serializable
            data class MoveLearnMethod(
                @SerialName("name")
                val name: String?,
                @SerialName("url")
                val url: String?
            )

            @Serializable
            data class VersionGroup(
                @SerialName("name")
                val name: String?,
                @SerialName("url")
                val url: String?
            )
        }
    }

    @Serializable
    data class Species(
        @SerialName("name")
        val name: String?,
        @SerialName("url")
        val url: String?
    )

    @Serializable
    data class Stat(
        @SerialName("base_stat")
        val baseStat: Float?,
        @SerialName("effort")
        val effort: Float?,
        @SerialName("stat")
        val stat: StatData?
    ) {
        @Serializable
        data class StatData(
            @SerialName("name")
            val name: String?,
            @SerialName("url")
            val url: String?
        )
    }

    @Serializable
    data class Type(
        @SerialName("slot")
        val slot: Int?,
        @SerialName("type")
        val type: TypeData?
    ) {
        @Serializable
        data class TypeData(
            @SerialName("name")
            val name: String?,
            @SerialName("url")
            val url: String?
        )
    }
}

/**
 * Map from DTO to domain model
 */
infix fun PokemonDetailsDTO?.toDomain(imageUrl: String): Pokemon =
    Pokemon(
        id = this?.id ?: 0,
        name = this?.name.orEmpty(),
        height = this?.height ?: 0,
        weight = this?.weight ?: 0,
        abilities = this?.abilities?.mapNotNull { it?.ability?.name }.orEmpty(),
        moves = this?.moves?.mapNotNull { it?.move?.name }.orEmpty(),
        stats = this?.stats
            ?.filter { it?.stat?.name?.isNotEmpty() == true }
            ?.mapNotNull { Pokemon.Stat(it?.baseStat ?: 0f, it?.stat?.name.orEmpty()) }.orEmpty(),
        types = this?.stats?.mapNotNull { it?.stat?.name }.orEmpty(),
        image = this?.id?.let { "${imageUrl}/$id.png" }.orEmpty()
    )