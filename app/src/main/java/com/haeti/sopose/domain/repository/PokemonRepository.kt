package com.haeti.sopose.domain.repository

import com.haeti.sopose.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>
}