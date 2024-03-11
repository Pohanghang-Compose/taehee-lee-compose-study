package com.haeti.sopose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.haeti.sopose.login.LoginScreen
import com.haeti.sopose.main.MainScreen
import com.haeti.sopose.signup.SignUpScreen

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.SignUp.route) {
            SignUpScreen()
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}