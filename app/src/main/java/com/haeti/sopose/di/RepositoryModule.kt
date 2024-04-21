package com.haeti.sopose.di

import com.haeti.sopose.data.repository.PokemonRepositoryImpl
import com.haeti.sopose.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl,
    ): PokemonRepository
}