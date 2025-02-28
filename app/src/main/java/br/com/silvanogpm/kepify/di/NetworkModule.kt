package br.com.silvanogpm.kepify.di

import br.com.silvanogpm.kepify.core.network.RetrofitInstance
import br.com.silvanogpm.kepify.core.network.service.cep.ViaCepService
import org.koin.dsl.module

val networkModule = module {
    single<ViaCepService> { RetrofitInstance.viaCepService }
}