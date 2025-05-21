package com.example.studies.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView



private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6B6969), // Cor escura para o gradiente
    secondary = Color(0xFFCDCDCD), // Cor clara para o gradiente
    tertiary = Color(0xFF424242)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B6969),
    secondary = Color(0xFFCDCDCD),
    tertiary = Color(0xFF424242)


)

@Composable
fun StudiesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFCDCDCD), Color(0xFF6B6969)),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
