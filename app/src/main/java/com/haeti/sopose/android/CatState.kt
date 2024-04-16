package com.haeti.sopose.android

import com.haeti.sopose.common.util.UiState
import com.haeti.sopose.domain.Cat

data class CatState(
    val uiState: UiState<List<Cat>> = UiState.Loading
)