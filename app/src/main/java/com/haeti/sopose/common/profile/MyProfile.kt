package com.haeti.sopose.common.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeti.sopose.common.components.ProfileImage
import com.haeti.sopose.extensions.HorizontalSpacer
import com.haeti.sopose.extensions.VerticalSpacer

@Composable
fun MyProfile(
    modifier: Modifier = Modifier,
    currentName: String,
    onNameChange: (String) -> Unit,
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var newName by rememberSaveable { mutableStateOf(currentName) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "이름 변경") },
            text = {
                Column {
                    Text(text = "변경할 이름을 입력해주세요.")
                    VerticalSpacer(height = 8.dp)
                    TextField(value = newName, onValueChange = { newName = it })
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onNameChange(newName)
                        showDialog = false
                    }
                ) {
                    Text(text = "확인")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "취소")
                }
            }
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { showDialog = true }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileImage(modifier = Modifier.size(60.dp))

        HorizontalSpacer(8.dp)

        Text(
            text = currentName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MyProfilePreview() {
    MyProfile(currentName = "이태희", onNameChange = {})
}