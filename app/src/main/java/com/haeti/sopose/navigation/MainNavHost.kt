package com.haeti.sopose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.haeti.sopose.android.AndroidScreen
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.home.HomeScreen
import com.haeti.sopose.login.LoginScreen
import com.haeti.sopose.mypage.MypageScreen
import com.haeti.sopose.signup.SignUpScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, authViewModel = viewModel)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController, authViewModel = viewModel)
        }
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.MyPage.route) {
            MypageScreen(navController = navController, authViewModel = viewModel)
        }
        composable(BottomNavItem.Android.route) {
            AndroidScreen(navController)
        }
    }
}