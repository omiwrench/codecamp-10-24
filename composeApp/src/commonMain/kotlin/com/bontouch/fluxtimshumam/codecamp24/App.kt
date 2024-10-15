package com.bontouch.fluxtimshumam.codecamp24

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bontouch.fluxtimshumam.codecamp24.model.ApiMovie
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val coroutineScope = rememberCoroutineScope()
    val api = MoviesApi()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var movies by remember { mutableStateOf<List<ApiMovie>?>(null) }
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                movies = api.getPopularMovies()
            }
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    movies?.forEach {
                        Text(it.title)
                    }
                }
            }
        }
    }
}