package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.BaseEndpoints.BRASIL_API_BASE_URL
import br.com.silvanogpm.kepify.core.network.BaseEndpoints.VIA_CEP_BASE_URL
import br.com.silvanogpm.kepify.core.network.KtorHttpClient
import br.com.silvanogpm.kepify.core.network.service.cep.response.BrasilApiCepResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorBrasilApiService : BrasilApiService {
    override suspend fun getCep(cep: String) =
        KtorHttpClient.httpClientAndroid
            .get("$BRASIL_API_BASE_URL/cep/v1/$cep")
            .body<BrasilApiCepResponse>()
}