package br.com.silvanogpm.kepify.core.network

import br.com.silvanogpm.kepify.core.network.service.cep.RetrofitViaCepService
import br.com.silvanogpm.kepify.core.network.service.cep.ViaCepService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val VIA_CEP_BASE_URL = "https://viacep.com.br/ws/"

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val viaCepService: ViaCepService by lazy {
        Retrofit.Builder()
            .baseUrl(VIA_CEP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitViaCepService::class.java)
    }
}