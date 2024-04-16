package com.haeti.sopose.android

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AndroidScreenViewModel @Inject constructor(

) : ContainerHost<CatState, CatSideEffect>, ViewModel() {

    override val container = container<CatState, CatSideEffect>(CatState())
}