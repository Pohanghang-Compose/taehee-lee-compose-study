package com.haeti.sopose.di

import com.haeti.sopose.data.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create()
}