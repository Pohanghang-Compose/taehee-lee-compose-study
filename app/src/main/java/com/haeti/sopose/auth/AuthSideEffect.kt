package com.haeti.sopose.auth

sealed class AuthSideEffect {
    object LoginSuccess: AuthSideEffect()
    object LoginFail: AuthSideEffect()
    object InvalidInputToast: AuthSideEffect()
    object SignUpSuccess: AuthSideEffect()

}