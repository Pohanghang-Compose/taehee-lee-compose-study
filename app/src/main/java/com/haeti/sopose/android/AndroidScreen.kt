package com.haeti.sopose.android

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeti.sopose.common.components.LoadingScreen
import com.haeti.sopose.common.components.PokemonProfile
import com.haeti.sopose.common.extensions.VerticalSpacer
import com.haeti.sopose.common.util.UiState
import com.haeti.sopose.domain.model.Pokemon
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AndroidScreen() {
    val viewModel: AndroidScreenViewModel = hiltViewModel()
    val state by viewModel.collectAsState()
    val context = LocalContext.current

    when (val uiState = state.uiState) {
        is UiState.Loading -> {
            LoadingScreen()
        }

        is UiState.Success -> {
            val pokemonList = uiState.data
            CatContents(pokemonList = pokemonList)
        }

        is UiState.Failure -> {
            // Error
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            is PokemonSideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}

@Composable
fun CatContents(
    pokemonList: List<Pokemon>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        VerticalSpacer(height = 20.dp)

        Text(
            text = "포켓몬 도감",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        VerticalSpacer(height = 16.dp)

        Box(modifier = Modifier.fillMaxSize()) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(6.dp)
            ) {
                items(pokemonList.size) { index ->
                    PokemonProfile(pokemon = pokemonList[index])
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CatContentPreview() {
    CatContents(
        pokemonList =
        listOf(
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
            Pokemon("피카츄", "https://pokeapi.co/api/v2/pokemon/25/"),
        ),
    )
}

