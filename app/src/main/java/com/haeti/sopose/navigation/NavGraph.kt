package com.haeti.sopose.navigation

import com.haeti.sopose.common.AUTH
import com.haeti.sopose.common.MAIN

sealed class NavGraph(val route: String) {
    data object Auth : NavGraph(route = AUTH)
    data object Main : NavGraph(route = MAIN)
}
