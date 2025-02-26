package br.com.silvanogpm.kepify.ui.component.form

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

typealias FieldIconFunction = @Composable (() -> Unit)?

@Composable
fun FieldIcon(isError: Boolean = false, fieldIcon: FieldIconFunction) {
    fieldIcon?.let {
        CompositionLocalProvider(LocalContentColor provides if (isError) MaterialTheme.colorScheme.error else LocalContentColor.current) {
            it()
        }
    }
}