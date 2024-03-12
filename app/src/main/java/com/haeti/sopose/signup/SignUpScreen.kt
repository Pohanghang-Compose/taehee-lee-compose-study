package com.haeti.sopose.signup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeti.sopose.common.components.TitleTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
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
                value = "",
                onValueChange = {}
            )

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "PW",
                hint = "비밀번호를 입력해주세요",
                value = "",
                onValueChange = {}
            )

            TitleTextField(
                modifier = Modifier.fillMaxWidth(),
                title = "닉네임",
                hint = "닉네임을 입력해주세요",
                value = "",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입 하기")
            }

        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignUpScreenPreview() {
    SignUpScreen()
}