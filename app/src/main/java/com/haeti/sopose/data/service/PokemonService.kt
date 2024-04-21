package com.haeti.sopose.data.service

import com.haeti.sopose.data.dto.PokemonResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonResponseDto>
}