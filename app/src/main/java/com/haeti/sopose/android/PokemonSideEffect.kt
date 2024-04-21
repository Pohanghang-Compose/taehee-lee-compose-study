package com.haeti.sopose.android

sealed class PokemonSideEffect {
    data class ShowToast(val message: String) : PokemonSideEffect()
}
