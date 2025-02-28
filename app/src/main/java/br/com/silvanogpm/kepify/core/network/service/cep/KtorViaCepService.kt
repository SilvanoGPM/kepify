package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.BaseEndpoints.VIA_CEP_BASE_URL
import br.com.silvanogpm.kepify.core.network.KtorHttpClient
import br.com.silvanogpm.kepify.core.network.service.cep.response.CepResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorViaCepService : ViaCepService {
    override suspend fun getCep(cep: String) =
        KtorHttpClient.httpClientAndroid.get("$VIA_CEP_BASE_URL/$cep/json").body<CepResponse>()

}