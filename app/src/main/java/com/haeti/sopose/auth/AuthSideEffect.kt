package com.haeti.sopose.auth

sealed class AuthSideEffect {
    object LoginSuccess: AuthSideEffect()
    object InvalidInputToast: AuthSideEffect()
    object SignUpSuccess: AuthSideEffect()
    object NavigateToSignUp: AuthSideEffect()

}