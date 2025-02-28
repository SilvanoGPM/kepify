package br.com.silvanogpm.kepify.ui.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.theme.Green300

@Composable
fun KepifyIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(8.dp),
    icon: @Composable () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    variant: ButtonVariant = ButtonVariant.SOLID,
) {
    val buttonValues: ButtonValues = getButtonVariantValues(variant)

    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = 48.dp),
        contentPadding = PaddingValues(0.dp),
        shape = shape,
        colors = buttonValues.colors,
        border = buttonValues.border,
        enabled = enabled && !isLoading,
    ) {
        if (!isLoading) {
            icon()
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp).testTag("isLoading"),
                color = Green300,
                trackColor = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun KepifyIconButtonPreview() {
    KepifyIconButton(
        onClick = {},
        icon = { Icon(Icons.Default.AddCircle, contentDescription = null) })
}

@Preview
@Composable
private fun KepifyIconButtonOutlinedPreview() {
    KepifyIconButton(
        onClick = {},
        icon = { Icon(Icons.Default.AddCircle, contentDescription = null) },
        variant = ButtonVariant.OUTLINED
    )
}


@Preview
@Composable
private fun KepifyIconButtonDisabledPreview() {
    KepifyIconButton(
        onClick = {},
        icon = { Icon(Icons.Default.AddCircle, contentDescription = null) },
        enabled = false,
    )
}

@Preview
@Composable
private fun KepifyIconButtonLoadingPreview() {
    KepifyIconButton(
        onClick = {},
        icon = { Icon(Icons.Default.AddCircle, contentDescription = null) },
        isLoading = true,
    )
}
