package br.com.silvanogpm.kepify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.silvanogpm.kepify.core.network.RetrofitInstance
import br.com.silvanogpm.kepify.core.network.service.cep.ViaCepService
import br.com.silvanogpm.kepify.ui.screen.home.HomeScreen
import br.com.silvanogpm.kepify.ui.screen.home.HomeViewModel
import br.com.silvanogpm.kepify.ui.theme.KepifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(RetrofitInstance.viaCepService) }

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

class HomeViewModelFactory(
    private val viaCepService: ViaCepService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(viaCepService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}