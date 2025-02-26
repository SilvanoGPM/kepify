package br.com.silvanogpm.kepify.ui.screen.home

sealed class HomeUiEvent {
    data class OnFetchAddress(val zipCode: String) : HomeUiEvent()
    data object OnReset : HomeUiEvent()
}