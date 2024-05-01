package com.haeti.sopose.common.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeti.sopose.R
import com.haeti.sopose.common.components.BirthdayCard
import com.haeti.sopose.common.components.ProfileImage
import com.haeti.sopose.common.extensions.HorizontalSpacer

@Composable
fun BirthdayProfile(
    modifier: Modifier = Modifier,
    birthday: String,
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

        Column {
            Text(
                text = "$name ${String(Character.toChars(0x1F382))}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = birthday, style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BirthdayCard()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BirthdayProfilePreview() {
    BirthdayProfile(image = R.drawable.img_fox, name = "최다민", birthday = "7월 16일")
}