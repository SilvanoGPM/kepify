package br.com.silvanogpm.kepify.core.network.service.cep.response

import br.com.silvanogpm.kepify.data.model.KepifyAddress

data class CepResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val unidade: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val estado: String,
    val regiao: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String,
) {
    fun toKepifyAddress() = KepifyAddress(
        zipCode = this.cep,
        state = this.estado,
        city = this.localidade,
        neighborhood = this.bairro,
        street = this.logradouro,
    )
}