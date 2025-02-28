package br.com.silvanogpm.kepify.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.theme.Green300
import br.com.silvanogpm.kepify.ui.theme.Green500

enum class ButtonVariant {
    SOLID,
    OUTLINED,
}

internal class ButtonValues(
    val colors: ButtonColors,
    val border: BorderStroke? = null,
)

@Composable
internal fun getButtonVariantValues(variant: ButtonVariant) = when (variant) {
    ButtonVariant.SOLID -> ButtonValues(
        colors = ButtonDefaults.buttonColors(
            containerColor = Green500,
            disabledContainerColor = Green300,
            disabledContentColor = Color.White,
        )
    )

    ButtonVariant.OUTLINED -> ButtonValues(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Green500,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Green300,
        ),
        border = BorderStroke(1.dp, Green500)
    )
}