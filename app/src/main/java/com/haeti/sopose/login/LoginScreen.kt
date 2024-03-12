package com.haeti.sopose.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.haeti.sopose.auth.AuthSideEffect
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.common.components.TitleTextField
import com.haeti.sopose.extensions.navigateSingleTopTo
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {
    val state by authViewModel.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var id: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Welcome to Sopose",
                    style = MaterialTheme.typography.headlineLarge
                )
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Spacer(modifier = Modifier.weight(0.3f))

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "ID",
                hint = "ID를 입력해주세요",
                value = id,
                onValueChange = { input -> id = input },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
            )

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "PW",
                hint = "비밀번호를 입력해주세요",
                value = password,
                onValueChange = { input -> password = input }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { authViewModel.navigateToSignUp() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입 하기")
            }

            Button(
                onClick = {
                    authViewModel.login(id, password)
                    Timber.e("id: $id, password: $password")
                    Timber.e("state id pw: ${state.id} ${state.password}")
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "로그인 하기")
            }

        }
    }

    authViewModel.collectSideEffect {
        when (it) {
            AuthSideEffect.NavigateToSignUp -> {
                navController.navigate("signup")
            }
            AuthSideEffect.LoginSuccess -> {
                Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                navController.navigate("main")
            }
            AuthSideEffect.InvalidInputToast -> {
                Toast.makeText(context, "정확한 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            else -> {
                // do nothing
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), authViewModel = hiltViewModel())
}