package com.haeti.sopose.navigation

sealed class Screen(val route: String) {
    data object SignUp : Screen("signup")
    data object Login : Screen("login")
}