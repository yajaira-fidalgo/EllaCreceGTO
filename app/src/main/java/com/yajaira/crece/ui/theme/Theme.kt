package com.yajaira.crece.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = MoradoPrimario,
    onPrimary = Color.White,
    primaryContainer = MoradoClaroContainer,
    onPrimaryContainer = MoradoOscuro,
    secondary = MoradoVariante,
    onSecondary = Color.White,
    background = FondoBlanco,
    onBackground = MoradoOscuro,
    surface = Color.White,
    onSurface = MoradoOscuro,
    onSurfaceVariant = TextoGrisSecundario,
    surfaceVariant = Color(0xFFF0E6F6)
)

@Composable
fun CreceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}