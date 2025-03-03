package br.com.silvanogpm.kepify.data.model

data class KepifyAddress(
    val zipCode: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
) {
    override fun toString() =
        "${this.street}, ${this.neighborhood}, ${this.city}, ${this.state}, Brazil - ${this.zipCode}"
}