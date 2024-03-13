package com.haeti.sopose.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleTextField(
    modifier: Modifier = Modifier,
    title: String,
    hint: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 2.dp)
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            label = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            },
            shape = RoundedCornerShape(4.dp),
        )
    }


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TitleTextFieldPreview() {
    TitleTextField(
        modifier = Modifier,
        title = "Title",
        hint = "제목을 입력하세요",
        value = "",
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        onValueChange = {}
    )
}
