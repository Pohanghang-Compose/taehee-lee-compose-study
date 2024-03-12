package com.haeti.sopose.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.haeti.sopose.auth.AuthSideEffect
import com.haeti.sopose.auth.AuthViewModel
import com.haeti.sopose.common.components.TitleTextField
import com.haeti.sopose.extensions.navigateSingleTopTo
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authState by authViewModel.collectAsState()
    val context = LocalContext.current
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Sign Up",
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


            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "ID",
                hint = "ID를 입력해주세요",
                value = authState.id,
                onValueChange = { id -> authViewModel.updateId(id) }
            )

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "PW",
                hint = "비밀번호를 입력해주세요",
                value = authState.password,
                onValueChange = { password -> authViewModel.updatePassword(password) }
            )

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "닉네임",
                hint = "닉네임을 입력해주세요",
                value = authState.nickname,
                onValueChange = { nickname -> authViewModel.updateNickname(nickname) }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { authViewModel.signUp() },
                modifier = Modifier.fillMaxWidth(),
                enabled = authState.isSignUpValid
            ) {
                Text(text = "회원가입 하기")
            }

        }
    }

    authViewModel.collectSideEffect {
        when (it) {
            AuthSideEffect.SignUpSuccess -> {
                navController.navigateSingleTopTo("login")
            }
            AuthSideEffect.InvalidInputToast -> {
                // 이 토스트가 현재는 뜰 수가 없지만 그냥 만듦
                Toast.makeText(context, "모든 값을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // do nothing
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignUpScreenPreview() {
    SignUpScreen()
}