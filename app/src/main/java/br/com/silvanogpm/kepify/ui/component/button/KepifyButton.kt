package br.com.silvanogpm.kepify.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.theme.Green500

@Composable
fun KepifyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Green500),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leadingIcon?.invoke()
            Text(text = text)
            trailingIcon?.invoke()
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