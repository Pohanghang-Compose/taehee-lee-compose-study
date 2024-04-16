package com.haeti.sopose.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeti.sopose.common.components.CatProfile
import com.haeti.sopose.common.extensions.VerticalSpacer
import com.haeti.sopose.common.util.UiState
import com.haeti.sopose.domain.Cat
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun AndroidScreen(
    viewModel: AndroidScreenViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    when (val uiState = state.uiState) {
        is UiState.Loading -> {
            // Loading
        }

        is UiState.Success -> {
            val catList = uiState.data
            CatContents(catList = catList)
        }

        is UiState.Failure -> {
            // Error
        }
    }
}

@Composable
fun CatContents(
    catList: List<Cat>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(height = 20.dp)

        Text(
            text = "고양이들",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        VerticalSpacer(height = 16.dp)

        Box(modifier = Modifier.fillMaxSize()) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(6.dp)
            ) {
                items(catList.size) { index ->
                    CatProfile(cat = catList[index])
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CatContentPreview() {
    CatContents(
        catList =
        listOf(
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
            Cat("Persian", image = "https://fakeimg.pl/500x500/cc5500"),
        ),
    )
}

