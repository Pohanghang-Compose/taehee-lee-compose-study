package com.haeti.sopose.android

import com.haeti.sopose.common.util.UiState
import com.haeti.sopose.domain.model.Pokemon

data class PokemonState(
    val uiState: UiState<List<Pokemon>> = UiState.Loading
)