package com.haeti.sopose.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haeti.sopose.common.util.UiState
import com.haeti.sopose.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AndroidScreenViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ContainerHost<PokemonState, PokemonSideEffect>, ViewModel() {

    override val container = container<PokemonState, PokemonSideEffect>(PokemonState())

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(uiState = UiState.Loading)
                }
            }

            val pokemonList = pokemonRepository.getPokemonList()

            intent {
                reduce {
                    state.copy(uiState = UiState.Success(pokemonList))
                }
            }
        }
    }
}