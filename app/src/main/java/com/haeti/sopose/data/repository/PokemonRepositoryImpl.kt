package com.haeti.sopose.data.repository

import com.haeti.sopose.data.service.PokemonService
import com.haeti.sopose.domain.model.Pokemon
import com.haeti.sopose.domain.model.toPokemon
import com.haeti.sopose.domain.repository.PokemonRepository
import timber.log.Timber
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val catService: PokemonService
) : PokemonRepository {
    override suspend fun getPokemonList(): List<Pokemon> = kotlin.runCatching {
        catService.getPokemonList()
    }.fold(
        onSuccess = { response ->
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toPokemon() } ?: emptyList()
            } else {
                emptyList()
            }
        },
        onFailure = {
            Timber.e(it)
            emptyList()
        }
    )
}
