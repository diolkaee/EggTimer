package com.example.eggtimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightBrown,
    primaryVariant = Color.White,
    onPrimary = Color.Black,
    secondary = Orange400,
    secondaryVariant = LightOrange,
    onSecondary = Color.Black,
    background = Yellow100
)

private val LightColorPalette = lightColors(
    primary = LightBrown,
    primaryVariant = Color.White,
    onPrimary = Color.Black,
    secondary = Orange400,
    secondaryVariant = LightOrange,
    onSecondary = Color.Black,
    background = Yellow100

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun EggTimerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}