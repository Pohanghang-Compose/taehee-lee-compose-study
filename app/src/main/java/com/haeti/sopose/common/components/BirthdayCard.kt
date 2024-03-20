package com.haeti.sopose.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BirthdayCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Red)
    ) {
        Text(
            text = "선물하기 ${String(Character.toChars(0x1F382))}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BirthdayCardPreview() {
    BirthdayCard()
}
