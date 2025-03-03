package br.com.silvanogpm.kepify.core.network.service.cep

import br.com.silvanogpm.kepify.core.network.service.cep.response.BrasilApiCepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBrasilApiService : BrasilApiService {
    @GET("cep/v1/{cep}")
    override suspend fun getCep(@Path("cep") cep: String): BrasilApiCepResponse
}