package com.haeti.sopose.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseDto(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<PokemonList>
)

@Serializable
data class PokemonList(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)