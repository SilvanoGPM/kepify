package br.com.silvanogpm.kepify.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object CepVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedCep = text
            .text
            .mapIndexed { index, char ->
                if (index == 4) "$char-" else char
            }
            .joinToString(separator = "")

        return TransformedText(AnnotatedString(text = transformedCep), CepOffsetMapping)
    }

}

object CepOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int =
        when {
            offset < 5 -> offset
            else -> offset + 1
        }

    override fun transformedToOriginal(offset: Int): Int =
        when {
            offset < 5 -> offset
            else -> offset - 1
        }

}