package com.haeti.sopose.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
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
        Timber.e("get savedStateHandle: ${savedStateHandle.get<String>("name")}")
    }

    fun updateName(name: String) = intent {
        savedStateHandle["name"] = name
        Timber.e("set savedStateHandle: ${savedStateHandle.get<String>("name")}")
        reduce {
            state.copy(name = name)
        }
        postSideEffect(HomeSideEffect.NameChangeToast(message = "이름이 변경되었습니다"))
    }
}