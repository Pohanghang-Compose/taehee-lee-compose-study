package com.haeti.sopose.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeti.sopose.R

@Composable
fun ProfileImage(
    image: Int = R.drawable.img_fox,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(48.dp)
            .clip(shape = RoundedCornerShape(24.dp))
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ProfileImagePreview() {
    ProfileImage(image = R.drawable.img_fox)
}