package com.haeti.sopose.common.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeti.sopose.R
import com.haeti.sopose.common.components.MusicCard
import com.haeti.sopose.common.components.ProfileImage
import com.haeti.sopose.extensions.HorizontalSpacer

@Composable
fun MusicProfile(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    music: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileImage(image)

        HorizontalSpacer(8.dp)

        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        MusicCard(music = music)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MusicProfilePreview() {
    MusicProfile(image = R.drawable.img_fox, name = "이태희", music = "봄날")
}