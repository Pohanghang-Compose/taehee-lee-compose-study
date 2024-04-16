package com.haeti.sopose.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeti.sopose.R
import com.haeti.sopose.android.paletteBackgroundColor
import com.haeti.sopose.common.extensions.VerticalSpacer
import com.haeti.sopose.domain.Cat
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun CatProfile(
    cat: Cat
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val backgroundColor by palette.paletteBackgroundColor()

    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = backgroundColor,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .size(100.dp),
            imageModel = { cat.image },
            imageOptions = ImageOptions(contentScale = ContentScale.Inside),
            component = rememberImageComponent {
                +CrossfadePlugin()
                +ShimmerPlugin(
                    Shimmer.Resonate(
                        baseColor = Color.Transparent,
                        highlightColor = Color.LightGray
                    )
                )

                if (!LocalInspectionMode.current) {
                    +PalettePlugin(
                        imageModel = cat.image,
                        useCache = true,
                        paletteLoadedListener = { palette = it }
                    )
                }
            },
            previewPlaceholder = painterResource(id = R.drawable.img_fox)
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(12.dp),
            text = cat.name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        VerticalSpacer(height = 8.dp)

    }

}

@Preview
@Composable
fun CatProfilePreview() {
    CatProfile(
        Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500")
    )
}