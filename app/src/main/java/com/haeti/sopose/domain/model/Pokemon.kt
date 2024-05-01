package com.haeti.sopose.domain.model

import com.haeti.sopose.data.dto.PokemonList

data class Pokemon(
    val name: String,
    val image: String
)

val PokemonList.imageUrl: String
    get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
        getIdFromUrl(
            this.url
        )
    }.png"

fun PokemonList.toPokemon(): Pokemon {
    return Pokemon(
        name = this.name,
        image = this.imageUrl
    )
}

fun getIdFromUrl(url: String): String {
    return url.trimEnd('/').substringAfterLast('/')
}
