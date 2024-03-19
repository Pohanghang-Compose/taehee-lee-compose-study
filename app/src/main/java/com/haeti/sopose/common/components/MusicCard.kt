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
fun MusicCard(
    modifier: Modifier = Modifier,
    music: String
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Green)
    ) {
        Text(
            text = "$music ${String(Character.toChars(0x1F3B5))}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MusicCardPreview() {
    MusicCard(music = "밤양갱 - 비비")
}