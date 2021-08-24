package com.flower.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

// example for app theme usage
private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Black,
    onPrimary = Black,
    secondary = Black,
    secondaryVariant = Black,
    onSecondary = Black,
    error = Black,
    onError = Black,
    background = Black,
    onBackground = Black,
    surface = Black,
    onSurface = Black,
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = White,
    onPrimary = White,
    secondary = White,
    secondaryVariant = White,
    onSecondary = White,
    error = White,
    onError = White,
    background = White,
    onBackground = White,
    surface = White,
    onSurface = White,
)

@Composable
fun FlowerSpotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = FlowerSpotTypography,
        shapes = FlowerSpotShapes,
        content = content
    )
}
