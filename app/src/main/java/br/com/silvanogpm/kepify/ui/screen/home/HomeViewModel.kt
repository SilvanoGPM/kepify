package br.com.silvanogpm.kepify.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.silvanogpm.kepify.core.network.service.cep.BrasilApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val brasilApiService: BrasilApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnFetchAddress -> fetchAddress(event.zipCode)
            HomeUiEvent.OnReset -> onReset()
        }
    }

    private fun fetchAddress(zipCode: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(status = HomeUiStateStatus.LOADING)
            }

            _uiState.update { currentUiState ->
                try {
                    val address = brasilApiService.getCep(cep = zipCode).toKepifyAddress()

                    currentUiState.copy(
                        status = HomeUiStateStatus.SUCCESS,
                        zipCode = zipCode,
                        state = address.state,
                        city = address.city,
                        neighborhood = address.neighborhood,
                        street = address.street,
                    )
                } catch (e: Exception) {
                    currentUiState.copy(status = HomeUiStateStatus.ERROR)
                }
            }
        }
    }

    private fun onReset() {
        viewModelScope.launch {
            _uiState.update { HomeUiState() }
        }
    }
}