package com.haeti.sopose.home

sealed class HomeSideEffect {
    data object NameChangeToast : HomeSideEffect()
}