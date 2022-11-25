package com.ab21.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Long = 0,
    val weight: Long = 0,
    val abilities: List<String> = listOf(),
    val moves: List<String> = listOf(),
    val stats: List<Stat> = listOf(),
    val types: List<String> = listOf(),
    val image: String = "",
    val isCaught: Boolean = false
) {

    data class Stat(
        val base: Float = 0f,
        val name: String = ""
    )
}

fun Pokemon.formattedId(): String = String.format("#%03d", id)
