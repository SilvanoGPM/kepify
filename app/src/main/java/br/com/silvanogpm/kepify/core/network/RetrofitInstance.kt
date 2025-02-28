package br.com.silvanogpm.kepify.core.network

import br.com.silvanogpm.kepify.core.network.BaseEndpoints.VIA_CEP_BASE_URL
import br.com.silvanogpm.kepify.core.network.service.cep.RetrofitViaCepService
import br.com.silvanogpm.kepify.core.network.service.cep.ViaCepService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val viaCepService: ViaCepService by lazy {
        getRetrofitInstance(VIA_CEP_BASE_URL)
            .create(RetrofitViaCepService::class.java)
    }

    private fun getRetrofitInstance(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}