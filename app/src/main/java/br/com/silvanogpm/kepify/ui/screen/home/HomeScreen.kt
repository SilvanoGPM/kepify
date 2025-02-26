package br.com.silvanogpm.kepify.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.component.button.KepifyButton
import br.com.silvanogpm.kepify.ui.component.form.BaseTextField
import br.com.silvanogpm.kepify.ui.transformation.CepVisualTransformation

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeUiEvent) -> Unit,
    uiState: HomeUiState
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var zipCode by remember(uiState.zipCode) { mutableStateOf(uiState.zipCode ?: "") }
        var state by remember(uiState.state) { mutableStateOf(uiState.state ?: "") }
        var city by remember(uiState.city) { mutableStateOf(uiState.city ?: "") }
        var neighborhood by remember(uiState.neighborhood) {
            mutableStateOf(
                uiState.neighborhood ?: ""
            )
        }
        var street by remember(uiState.street) { mutableStateOf(uiState.street ?: "") }

        Row(modifier = Modifier.fillMaxWidth()) {
            when (uiState.status) {
                HomeUiStateStatus.LOADING -> CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                HomeUiStateStatus.ERROR -> Text(
                    text = "Aconteceu um erro ao carregar o endereço!",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )

                else -> Unit
            }
        }


        KepifyButton(text = "Pesquisar", onClick = {
            focusManager.clearFocus()
            onEvent(HomeUiEvent.OnFetchAddress(zipCode))
        })

        BaseTextField(
            label = "CEP",
            value = zipCode,
            onValueChange = { zipCode = it },
            keyboardType = KeyboardType.Number,
            visualTransformation = CepVisualTransformation
        )

        BaseTextField(label = "Estado", value = state, onValueChange = { state = it })
        BaseTextField(label = "Cidade", value = city, onValueChange = { city = it })
        BaseTextField(label = "Bairro", value = neighborhood, onValueChange = { neighborhood = it })
        BaseTextField(label = "Rua", value = street, onValueChange = { street = it })

        KepifyButton(text = "Resetar", onClick = {
            onEvent(HomeUiEvent.OnReset)
        })
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onEvent = {},
        uiState = HomeUiState()
    )
}