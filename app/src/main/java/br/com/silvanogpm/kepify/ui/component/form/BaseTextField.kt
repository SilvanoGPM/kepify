package br.com.silvanogpm.kepify.ui.component.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    onImeAction: (() -> Unit)? = null,
    leadingIcon: FieldIconFunction = null,
    trailingIcon: FieldIconFunction = null,
    errorMessage: String? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val focusManager = LocalFocusManager.current

    val isError = errorMessage != null

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        leadingIcon = if (leadingIcon != null) ({
            FieldIcon(
                isError = isError,
                fieldIcon = leadingIcon
            )
        }) else null,
        trailingIcon = if (trailingIcon != null) ({
            FieldIcon(
                isError = isError,
                fieldIcon = trailingIcon
            )
        }) else null,
        keyboardActions = KeyboardActions(
            onAny = { onImeAction?.invoke() ?: focusManager.moveFocus(FocusDirection.Down) }
        ),
        supportingText = if (isError) ({
            Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
        }) else null,
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Preview
@Composable
private fun BaseTextFieldPreview(
) {
    var email by remember { mutableStateOf("") }

    BaseTextField(
        value = email,
        onValueChange = { email = it },
        label = "E-mail",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Símbolo do e-mail"
            )
        },
    )
}

@Preview
@Composable
private fun BaseTextFieldWithErrorPreview(
) {
    var email by remember { mutableStateOf("") }

    BaseTextField(
        value = email,
        onValueChange = { email = it },
        label = "E-mail",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Símbolo do e-mail"
            )
        },
        errorMessage = "Aconteceu um problema"
    )
}
