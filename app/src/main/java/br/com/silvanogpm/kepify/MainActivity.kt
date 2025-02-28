package br.com.silvanogpm.kepify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.silvanogpm.kepify.ui.screen.home.HomeScreen
import br.com.silvanogpm.kepify.ui.screen.home.HomeViewModel
import br.com.silvanogpm.kepify.ui.theme.KepifyTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeViewModel = koinViewModel<HomeViewModel>()
            val homeUiState by homeViewModel.uiState.collectAsState()

            KepifyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    HomeScreen(
                        modifier = Modifier.padding(padding),
                        uiState = homeUiState,
                        onEvent = homeViewModel::onEvent
                    )
                }
            }
        }
    }
}
