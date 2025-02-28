package br.com.silvanogpm.kepify

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import br.com.silvanogpm.kepify.ui.screen.home.HomeScreen
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiEvent
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiState
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiStateStatus
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_show_empty_cep_field_on_initial_state() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState()
            )
        }

        getCEPTextField().assertIsDisplayed()
        getSearchButton().assertIsDisplayed()
    }

    @Test
    fun should_show_loading_indicator_when_status_is_loading() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState(status = HomeUiStateStatus.LOADING)
            )
        }

        getSearchButton().assertIsDisplayed()
        composeTestRule.onNodeWithText("Carregando informações...").assertIsDisplayed()

        getErrorMessage().assertIsNotDisplayed()
    }

    @Test
    fun should_show_reaming_screen_when_status_is_success() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState(
                    status = HomeUiStateStatus.SUCCESS,
                )
            )
        }

        composeTestRule.onNodeWithText("Estado").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cidade").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bairro").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rua").assertIsDisplayed()

        getResetButton().assertIsDisplayed()
        getShareButton().assertIsDisplayed()

        getErrorMessage().assertIsNotDisplayed()
    }

    @Test
    fun should_show_error_message_when_status_is_error() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState(status = HomeUiStateStatus.ERROR)
            )
        }

        getErrorMessage().assertIsDisplayed()
    }

    @Test
    fun should_trigger_fetch_event_when_search_button_is_clicked() {
        val mockOnEvent: (HomeUiEvent) -> Unit = mockk()
        every { mockOnEvent(any()) } just Runs

        composeTestRule.setContent {
            HomeScreen(
                onEvent = mockOnEvent,
                uiState = HomeUiState()
            )
        }

        getCEPTextField().performTextInput("12345678")
        getSearchButton().performClick()

        verify { mockOnEvent(HomeUiEvent.OnFetchAddress("12345678")) }
        confirmVerified(mockOnEvent)
    }

    @Test
    fun should_apply_cep_visual_transformation() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState()
            )
        }

        getCEPTextField().performTextInput("12345678")

        composeTestRule.onNodeWithText("12345-678").assertExists()
    }

    @Test
    fun should_fill_the_fields_when_uiState_has_data() {
        composeTestRule.setContent {
            HomeScreen(
                onEvent = {},
                uiState = HomeUiState(
                    status = HomeUiStateStatus.SUCCESS,
                    zipCode = "12345678",
                    state = "SP",
                    city = "São Paulo",
                    neighborhood = "Centro",
                    street = "Rua Teste"
                )
            )
        }

        composeTestRule.onNodeWithText("SP").assertExists()
        composeTestRule.onNodeWithText("São Paulo").assertExists()
        composeTestRule.onNodeWithText("Centro").assertExists()
        composeTestRule.onNodeWithText("Rua Teste").assertExists()
    }

    @Test
    fun should_trigger_reset_event_when_reset_button_is_clicked() {
        val mockOnEvent: (HomeUiEvent) -> Unit = mockk()
        every { mockOnEvent(any()) } just Runs

        composeTestRule.setContent {
            HomeScreen(
                onEvent = mockOnEvent,
                uiState = HomeUiState(
                    status = HomeUiStateStatus.SUCCESS,
                    zipCode = "12345678",
                    state = "SP",
                    city = "São Paulo",
                    neighborhood = "Centro",
                    street = "Rua Teste"
                )
            )
        }

        getResetButton().performClick()

        verify { mockOnEvent(HomeUiEvent.OnReset) }
        confirmVerified(mockOnEvent)
    }

    private fun getCEPTextField() = composeTestRule.onNodeWithText("Insira o CEP")
    private fun getSearchButton() = composeTestRule.onNodeWithTag("Pesquisar")
    private fun getResetButton() = composeTestRule.onNodeWithText("Resetar")
    private fun getShareButton() = composeTestRule.onNodeWithText("Compartilhar")

    private fun getErrorMessage() =
        composeTestRule
            .onNodeWithText("Não foi possível carregar endereço, tente novamente.")
}