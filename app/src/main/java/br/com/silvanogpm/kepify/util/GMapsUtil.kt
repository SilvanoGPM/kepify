package br.com.silvanogpm.kepify.util

import android.util.Log
import br.com.silvanogpm.kepify.BuildConfig
import br.com.silvanogpm.kepify.data.model.KepifyAddress
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class ImageDirection(val heading: Int) {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);
}

fun getAddressImage(address: KepifyAddress, imageDirection: ImageDirection): String {
    val gmapsBaseUrl =
        "https://maps.googleapis.com/maps/api/streetview?size=1200x800&key=${BuildConfig.GOOGLE_API_KEY}"
    val encodedLocation = URLEncoder.encode(address.toString(), StandardCharsets.UTF_8.toString())
    val urlWithData = "$gmapsBaseUrl&heading=${imageDirection.heading}&location=$encodedLocation"

    Log.i("getAddressImage", ": ${urlWithData}.jpg")

    return "$urlWithData.jpg"
}