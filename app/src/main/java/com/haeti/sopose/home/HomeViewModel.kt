package com.haeti.sopose.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    init {
        savedStateHandle.get<String>("name")?.let { name ->
            updateName(name)
        }
    }

    fun updateName(name: String) = intent {
        savedStateHandle["name"] = name
        reduce {
            state.copy(name = name)
        }
        postSideEffect(HomeSideEffect.NameChangeToast)
    }
}