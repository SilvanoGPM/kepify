package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.service.cep.response.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitViaCepService : ViaCepService {
    @GET("{cep}/json")
    override suspend fun getCep(@Path("cep") cep: String): CepResponse
}