package br.com.silvanogpm.kepify.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun shareTextAndImage(context: Context, text: String, imageUri: Uri? = null) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        if (imageUri != null)  {
            type = "image/*" // Define o tipo de conteúdo como imagem
            putExtra(Intent.EXTRA_STREAM, imageUri)// Adiciona a imagem
        }

        putExtra(Intent.EXTRA_TEXT, text) // Adiciona o texto
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Permissão para compartilhar a URI
    }

    context.startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
}