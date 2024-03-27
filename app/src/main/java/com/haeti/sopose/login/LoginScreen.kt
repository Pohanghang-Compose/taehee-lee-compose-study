package com.haeti.sopose.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.haeti.sopose.auth.AuthSideEffect
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.common.components.TitleTextField
import com.haeti.sopose.extensions.addFocusCleaner
import com.haeti.sopose.navigation.NavGraph
import com.haeti.sopose.navigation.Screen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val state by authViewModel.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var id: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .addFocusCleaner(focusManager),
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
        )

        TitleTextField(
            modifier = Modifier.fillMaxWidth(),
            title = "PW",
            hint = "비밀번호를 입력해주세요",
            value = password,
            onValueChange = { input -> password = input },
            keyboardType = KeyboardType.Password
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

    authViewModel.collectSideEffect {
        when (it) {
            AuthSideEffect.NavigateToSignUp -> {
                navController.navigate(Screen.SignUp.route)
            }

            AuthSideEffect.LoginSuccess -> {
                Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                navController.navigate(NavGraph.Main.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
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