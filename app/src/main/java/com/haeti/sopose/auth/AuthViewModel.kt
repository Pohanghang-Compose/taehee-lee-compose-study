package com.haeti.sopose.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ContainerHost<AuthState, AuthSideEffect>, ViewModel() {
    override val container = container<AuthState, AuthSideEffect>(AuthState())

    fun updateId(id: String) = intent {
        reduce {
            state.copy(id = id).validateSignUp()
        }
    }

    fun updatePassword(password: String) = intent {
        reduce {
            state.copy(password = password).validateSignUp()
        }
    }

    fun updateNickname(nickname: String) = intent {
        reduce {
            state.copy(nickname = nickname).validateSignUp()
        }
    }

    fun signUp() = intent {
        if (!state.isSignUpValid) {
            postSideEffect(AuthSideEffect.InvalidInputToast)
        } else {
            postSideEffect(AuthSideEffect.SignUpSuccess)
        }
    }

    fun login(id: String, password: String) = intent {
        if (state.id == id && state.password == password) {
            postSideEffect(AuthSideEffect.LoginSuccess)
        } else {
            postSideEffect(AuthSideEffect.InvalidInputToast)
        }
    }


}

fun AuthState.validateSignUp() = this.copy(
    isSignUpValid = id.isNotEmpty() && password.isNotEmpty() && nickname.isNotEmpty()
)
