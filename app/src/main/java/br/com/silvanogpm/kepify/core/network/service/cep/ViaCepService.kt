package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.service.cep.response.ViaCepResponse

interface ViaCepService {
    suspend fun getCep(cep: String): ViaCepResponse
}

