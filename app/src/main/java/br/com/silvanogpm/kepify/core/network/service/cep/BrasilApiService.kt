package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.service.cep.response.BrasilApiCepResponse

interface BrasilApiService {
    suspend fun getCep(cep: String): BrasilApiCepResponse
}
