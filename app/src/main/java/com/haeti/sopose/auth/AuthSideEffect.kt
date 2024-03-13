package com.haeti.sopose.auth

sealed class AuthSideEffect {
    data object LoginSuccess : AuthSideEffect()
    data object InvalidInputToast : AuthSideEffect()
    data object SignUpSuccess : AuthSideEffect()
    data object NavigateToSignUp : AuthSideEffect()
}