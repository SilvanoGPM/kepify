package br.com.silvanogpm.kepify.ui.screen.home

data class HomeUiState(
    val zipCode: String? = null,
    val state: String? = null,
    val city: String? = null,
    val neighborhood: String? = null,
    val street: String? = null,

    val status: HomeUiStateStatus = HomeUiStateStatus.NONE,
)

enum class HomeUiStateStatus {
    SUCCESS,
    LOADING,
    ERROR,
    NONE,
}
