package com.haeti.sopose.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeti.sopose.R
import com.haeti.sopose.auth.AuthViewModel
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun MypageScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_fox),
                contentDescription = "fox",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Text(
                text = authState.nickname,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "정신 나갈 것 같에 이 각박한 세상이..",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.End,
                    color = Color.Gray
                ),
            )
        }

        Spacer(modifier = Modifier.size(48.dp))

        Text(
            text = "ID",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(
            text = authState.id,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            ),
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MypageScreen(authViewModel = hiltViewModel())
}