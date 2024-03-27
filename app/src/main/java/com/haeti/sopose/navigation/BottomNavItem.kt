package com.haeti.sopose.navigation

import com.haeti.sopose.R
import com.haeti.sopose.common.ANDROID
import com.haeti.sopose.common.HOME
import com.haeti.sopose.common.MYPAGE

sealed class BottomNavItem(
    val title: Int, val icon: Int, val route: String
) {
    data object Android : BottomNavItem(
        title = R.string.android, icon = R.drawable.ic_android, route = ANDROID
    )

    data object Home : BottomNavItem(
        title = R.string.home, icon = R.drawable.ic_home, route = HOME
    )

    data object MyPage : BottomNavItem(
        title = R.string.my_page, icon = R.drawable.ic_account, route = MYPAGE
    )

    // 요래 하니 NPE 뜬다..
    companion object {
        val items = listOf(Android, Home, MyPage)
    }
}

fun BottomNavItem.Companion.isInBottomNav(route: String?): Boolean {
    return route?.let { items.any { it.route == route } } ?: false
}
