package br.com.silvanogpm.kepify.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.component.button.ButtonVariant
import br.com.silvanogpm.kepify.ui.component.button.KepifyButton
import br.com.silvanogpm.kepify.ui.component.button.KepifyIconButton
import br.com.silvanogpm.kepify.ui.component.form.BaseTextField
import br.com.silvanogpm.kepify.ui.screen.home.component.HomeHero
import br.com.silvanogpm.kepify.ui.theme.Typography
import br.com.silvanogpm.kepify.ui.transformation.CepVisualTransformation

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onEvent: (HomeUiEvent) -> Unit,
    uiState: HomeUiState
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeHero()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),

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

            val zipCodeIsValid = zipCode.length >= 8

            fun handleSearchAddress() {
                if (!zipCodeIsValid) return

                focusManager.clearFocus()
                onEvent(HomeUiEvent.OnFetchAddress(zipCode))
            }

            Column {
                if (uiState.status == HomeUiStateStatus.ERROR) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        text = "Não foi possível carregar endereço, tente novamente.",
                        textAlign = TextAlign.Center,
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Row(
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    BaseTextField(
                        label = "Insira o CEP",
                        value = zipCode,
                        onValueChange = { zipCode = it },
                        keyboardType = KeyboardType.Number,
                        visualTransformation = CepVisualTransformation,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
                        onImeAction = ::handleSearchAddress,
                        imeAction = ImeAction.Search
                    )

                    KepifyIconButton(
                        modifier = Modifier.testTag("Pesquisar").fillMaxHeight(),
                        shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
                        onClick = ::handleSearchAddress,
                        enabled = zipCodeIsValid,
                        isLoading = uiState.status == HomeUiStateStatus.LOADING,
                        icon = {
                            Icon(Icons.Default.Search, contentDescription = "Ícone de lupa")
                        }
                    )
                }

                if (uiState.status == HomeUiStateStatus.LOADING) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        text = "Carregando informações...",
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    )
                }
            }

            if (uiState.status == HomeUiStateStatus.SUCCESS) {
                BaseTextField(label = "Estado", value = state, onValueChange = { state = it })
                BaseTextField(label = "Cidade", value = city, onValueChange = { city = it })

                BaseTextField(
                    label = "Bairro",
                    value = neighborhood,
                    onValueChange = { neighborhood = it })

                BaseTextField(label = "Rua", value = street, onValueChange = { street = it })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    KepifyButton(
                        variant = ButtonVariant.OUTLINED,
                        modifier = Modifier.weight(1f),
                        text = "Resetar",
                        onClick = {
                            onEvent(HomeUiEvent.OnReset)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Restore,
                                contentDescription = "Ícone de fecha realizando uma volta"
                            )
                        }
                    )

                    KepifyButton(
                        modifier = Modifier.weight(1f),
                        text = "Compartilhar",
                        onClick = {},
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Ícone de compartilhamento de localização"
                            )
                        }
                    )
                }
            }
        }
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