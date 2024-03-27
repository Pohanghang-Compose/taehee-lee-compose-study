package com.haeti.sopose.home

sealed class HomeSideEffect {
    data class NameChangeToast(val message: String) : HomeSideEffect()
}