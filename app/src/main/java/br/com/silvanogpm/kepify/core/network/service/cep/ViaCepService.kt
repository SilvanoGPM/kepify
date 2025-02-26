package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.service.cep.response.CepResponse

interface ViaCepService {
    suspend fun getCep(cep: String): CepResponse
}

