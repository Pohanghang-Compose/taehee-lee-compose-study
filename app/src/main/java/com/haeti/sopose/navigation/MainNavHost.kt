package com.haeti.sopose.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.haeti.sopose.android.AndroidScreen
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.home.HomeScreen
import com.haeti.sopose.login.LoginScreen
import com.haeti.sopose.mypage.MypageScreen
import com.haeti.sopose.signup.SignUpScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Login.route -> {
                    CenterAlignedTopAppBar(title = {
                        Text(
                            text = "Welcome to Sopose",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    })
                }

                Screen.SignUp.route -> {
                    CenterAlignedTopAppBar(title = {
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                    })
                }
            }
        },
        bottomBar = {
            if (BottomNavItem.isInBottomNav(currentRoute)) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = NavGraph.Auth.route
        ) {
            authGraph(
                navController = navController,
                authViewModel = viewModel,
                modifier = Modifier.padding(it)
            )
            mainGraph(authViewModel = viewModel)
        }
    }

}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier
) {
    navigation(startDestination = Screen.Login.route, route = NavGraph.Auth.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel,
                modifier = modifier
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController = navController,
                authViewModel = authViewModel,
                modifier = modifier
            )
        }
    }
}

fun NavGraphBuilder.mainGraph(authViewModel: AuthViewModel) {
    navigation(startDestination = BottomNavItem.Home.route, route = NavGraph.Main.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.MyPage.route) {
            MypageScreen(authViewModel = authViewModel)
        }
        composable(BottomNavItem.Android.route) {
            AndroidScreen()
        }
    }
}