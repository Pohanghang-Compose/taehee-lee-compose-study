package com.haeti.sopose.common.profile

import androidx.compose.foundation.layout.Row
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
import com.haeti.sopose.common.components.ProfileImage
import com.haeti.sopose.common.extensions.HorizontalSpacer

@Composable
fun FriendProfile(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
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
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FriendProfilePreview() {
    FriendProfile(image = R.drawable.img_fox, name = "최다민")
}
