package com.haeti.sopose.auth

data class AuthState (
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val isSignUpValid: Boolean = false,
)