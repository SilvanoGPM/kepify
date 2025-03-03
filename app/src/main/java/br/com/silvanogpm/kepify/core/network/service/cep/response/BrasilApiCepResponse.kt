package br.com.silvanogpm.kepify.core.network.service.cep.response

import br.com.silvanogpm.kepify.data.model.KepifyAddress
import kotlinx.serialization.Serializable

@Serializable
data class BrasilApiCepResponse(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
) {
    fun toKepifyAddress() = KepifyAddress(
        zipCode = this.cep,
        state = this.state,
        city = this.city,
        neighborhood = this.neighborhood,
        street = this.street,
    )
}