package com.haeti.sopose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
        startDestination = NavGraph.Auth.route
    ) {
        authGraph(navController = navController, authViewModel = viewModel)
        mainGraph(navController = navController, authViewModel = viewModel)
    }
}

fun NavGraphBuilder.authGraph(navController: NavController, authViewModel: AuthViewModel) {
    navigation(startDestination = Screen.Login.route, route = NavGraph.Auth.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController, authViewModel: AuthViewModel) {
    navigation(startDestination = BottomNavItem.Home.route, route = NavGraph.Main.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.MyPage.route) {
            MypageScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(BottomNavItem.Android.route) {
            AndroidScreen(navController)
        }
    }
}