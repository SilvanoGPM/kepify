package br.com.silvanogpm.kepify.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.theme.Green300

@Composable
fun KepifyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingMessage: String = "Carregando...",
    variant: ButtonVariant = ButtonVariant.SOLID,
) {
    val buttonValues = getButtonVariantValues(variant)

    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.heightIn(min = 48.dp),
        shape = RoundedCornerShape(8.dp),
        border = buttonValues.border,
        colors = buttonValues.colors,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!isLoading) {
                leadingIcon?.invoke()
                Text(text = text)
                trailingIcon?.invoke()
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = Green300,
                    trackColor = Color.White,
                )

                Text(text = loadingMessage)
            }
        }
    }
}

@Preview
@Composable
private fun KepifyButtonPreview() {
    KepifyButton(text = "Clique aqui", onClick = {})
}

@Preview
@Composable
private fun KepifyButtonOutlinedPreview() {
    KepifyButton(text = "Clique aqui", onClick = {}, variant = ButtonVariant.OUTLINED)
}

@Preview
@Composable
private fun KepifyButtonWithLeadingIconPreview() {
    KepifyButton(
        text = "Adicionar",
        onClick = {},
        leadingIcon = { Icon(Icons.Default.AddCircle, contentDescription = null) }
    )
}

@Preview
@Composable
private fun KepifyButtonWithTrailingIconPreview() {
    KepifyButton(
        text = "Confirmar",
        onClick = {},
        trailingIcon = { Icon(Icons.Default.CheckCircle, contentDescription = null) }
    )
}

@Preview
@Composable
private fun KepifyButtonDisabledPreview() {
    KepifyButton(
        text = "Desativado",
        onClick = {},
        enabled = false,
    )
}

@Preview
@Composable
private fun KepifyButtonLoadingPreview() {
    KepifyButton(
        text = "Carregando",
        onClick = {},
        isLoading = true,
    )
}