package com.haeti.sopose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.haeti.sopose.android.AndroidScreen
import com.haeti.sopose.android.AndroidScreenViewModel
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.home.HomeScreen
import com.haeti.sopose.home.HomeViewModel
import com.haeti.sopose.login.LoginScreen
import com.haeti.sopose.mypage.MypageScreen
import com.haeti.sopose.signup.SignUpScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val homeViewModel: HomeViewModel = hiltViewModel()
    val androidScreenViewModel: AndroidScreenViewModel = hiltViewModel()

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
            startDestination = NavGraph.Main.route
        ) {
            authGraph(
                navController = navController,
                authViewModel = viewModel,
                modifier = Modifier.padding(it)
            )
            mainGraph(
                authViewModel = viewModel,
                homeViewModel = homeViewModel,
                androidScreenViewModel = androidScreenViewModel,
                modifier = Modifier.padding(it)
            )
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

fun NavGraphBuilder.mainGraph(
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    androidScreenViewModel: AndroidScreenViewModel,
    modifier: Modifier
) {
    navigation(startDestination = BottomNavItem.Home.route, route = NavGraph.Main.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(modifier, homeViewModel)
        }
        composable(BottomNavItem.MyPage.route) {
            MypageScreen(modifier, authViewModel)
        }
        composable(BottomNavItem.Android.route) {
            AndroidScreen(modifier, androidScreenViewModel)
        }
    }
}