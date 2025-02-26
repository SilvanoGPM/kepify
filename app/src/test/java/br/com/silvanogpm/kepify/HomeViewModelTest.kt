package br.com.silvanogpm.kepify

import app.cash.turbine.test
import br.com.silvanogpm.kepify.core.network.service.cep.ViaCepService
import br.com.silvanogpm.kepify.core.network.service.cep.response.CepResponse
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiEvent
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiState
import br.com.silvanogpm.kepify.ui.screen.home.HomeUiStateStatus
import br.com.silvanogpm.kepify.ui.screen.home.HomeViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val viaCepService = mockk<ViaCepService>()

    @Before
    fun setup() {
        viewModel = HomeViewModel(viaCepService)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `OnFetchAddress should update status to SUCCESS and fetch CEP data, when the zipCode is valid`() =
        runTest {
            coEvery { viaCepService.getCep(any()) } returns mockAddress

            viewModel.uiState.test {
                assertEquals(HomeUiState(), awaitItem())

                viewModel.onEvent(HomeUiEvent.OnFetchAddress("00000-000"))
                assertEquals(HomeUiStateStatus.LOADING, awaitItem().status)

                val successState = awaitItem()
                assertEquals(HomeUiStateStatus.SUCCESS, successState.status)
                assertEquals("00000-000", successState.zipCode)
                assertEquals("São Paulo", successState.state)
                assertEquals("São Paulo", successState.city)
                assertEquals("Centro", successState.neighborhood)
                assertEquals("Rua A", successState.street)

                cancel()
            }
        }

    @Test
    fun `OnFetchAddress should update status to ERROR, when zipCode is invalid or has some network problem`() =
        runTest {
            coEvery { viaCepService.getCep(any()) } throws Exception("Zip Code Invalid or Network Problem")

            viewModel.uiState.test {
                assertEquals(HomeUiState(), awaitItem())

                viewModel.onEvent(HomeUiEvent.OnFetchAddress("00000-000"))

                assertEquals(HomeUiStateStatus.LOADING, awaitItem().status)
                assertEquals(HomeUiStateStatus.ERROR, awaitItem().status)

                cancel()
            }
        }

    @Test
    fun `OnReset should reset the state to initial values`() = runTest {
        coEvery { viaCepService.getCep(any()) } returns mockAddress

        viewModel.uiState.test {
            assertEquals(HomeUiState(), awaitItem())

            viewModel.onEvent(HomeUiEvent.OnFetchAddress("00000-000"))
            awaitItem() // LOADING
            awaitItem() // SUCCESS

            viewModel.onEvent(HomeUiEvent.OnReset)
            assertEquals(HomeUiState(), awaitItem())

            cancel()
        }
    }

    private val mockAddress = CepResponse(
        cep = "00000-000",
        logradouro = "Rua A",
        bairro = "Centro",
        localidade = "São Paulo",
        uf = "SP",
        ibge = "0000",
        ddd = "00",
        gia = "00",
        complemento = "Complemento",
        siafi = "00",
        estado = "São Paulo",
        regiao = "Sudeste",
        unidade = ""
    )
}