package me.kofesst.android.shoppinglist.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Suppress("OPT_IN_IS_NOT_ENABLED")
class TextFields private constructor() {
    companion object {
        @Composable
        fun OutlinedPasswordTextField(
            modifier: Modifier = Modifier,
            value: String = "",
            onValueChange: (String) -> Unit = {},
            isReadOnly: Boolean = false,
            errorMessage: String? = null,
            label: String = "",
            leadingIcon: Painter? = null,
            singleLine: Boolean = true,
            textStyle: TextStyle = MaterialTheme.typography.bodyLarge
        ) {
            var passwordVisible by remember {
                mutableStateOf(false)
            }
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                isReadOnly = isReadOnly,
                errorMessage = errorMessage,
                label = label,
                leadingIcon = leadingIcon,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = modifier,
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = rememberVectorPainter(
                    if (passwordVisible) {
                        Icons.Outlined.Visibility
                    } else {
                        Icons.Outlined.VisibilityOff
                    }
                ),
                onTrailingIconClick = {
                    passwordVisible = !passwordVisible
                }
            )
        }

        @Composable
        fun OutlinedNumericTextField(
            modifier: Modifier = Modifier,
            value: Int? = 0,
            onValueChange: (Int?) -> Unit = {},
            isReadOnly: Boolean = false,
            errorMessage: String? = null,
            label: String = "",
            leadingIcon: Painter? = null,
            trailingIcon: Painter? = null,
            onTrailingIconClick: () -> Unit = {},
            singleLine: Boolean = true,
            textStyle: TextStyle = MaterialTheme.typography.bodyLarge
        ) {
            OutlinedTextField(
                value = value?.toString() ?: "",
                onValueChange = {
                    onValueChange(
                        if (it.isBlank()) {
                            null
                        } else {
                            it.toIntOrNull() ?: value
                        }
                    )
                },
                isReadOnly = isReadOnly,
                errorMessage = errorMessage,
                label = label,
                leadingIcon = leadingIcon,
                singleLine = singleLine,
                trailingIcon = trailingIcon,
                onTrailingIconClick = onTrailingIconClick,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = textStyle,
                modifier = modifier
            )
        }

        @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
        @Composable
        fun OutlinedTextField(
            modifier: Modifier = Modifier,
            value: String = "",
            onValueChange: (String) -> Unit = {},
            isReadOnly: Boolean = false,
            errorMessage: String? = null,
            label: String = "",
            leadingIcon: Painter? = null,
            trailingIcon: Painter? = null,
            onTrailingIconClick: () -> Unit = {},
            singleLine: Boolean = true,
            textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
            visualTransformation: VisualTransformation = VisualTransformation.None
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            Column(modifier = modifier) {
                OutlinedTextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    readOnly = isReadOnly,
                    isError = errorMessage != null,
                    label = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    leadingIcon = if (leadingIcon != null) {
                        {
                            Icon(
                                painter = leadingIcon,
                                contentDescription = null
                            )
                        }
                    } else {
                        null
                    },
                    singleLine = singleLine,
                    trailingIcon = if (trailingIcon != null) {
                        {
                            IconButton(onClick = onTrailingIconClick) {
                                Icon(
                                    painter = trailingIcon,
                                    contentDescription = null
                                )
                            }
                        }
                    } else {
                        null
                    },
                    textStyle = textStyle,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    visualTransformation = visualTransformation,
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldError(
                    modifier = Modifier.fillMaxWidth(),
                    message = errorMessage
                )
            }
        }

        @Composable
        private fun TextFieldError(
            modifier: Modifier = Modifier,
            message: String? = null
        ) {
            AnimatedVisibility(visible = message != null) {
                Text(
                    text = message ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                )
            }
        }
    }
}