package com.example.grailed.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val VampireBlack = Color(0xFF080808)
val ChineseBlack = Color(0xFF121212)
val AntiFlashWhite = Color(0xFFFFFFFF)
val LightGray = Color(0xFFF9F9F9)
val X11Gray = Color(0xFFBDBDBD)
val X11DarkGray = Color(0xFFAAAAAA)

val DarkColorScheme = darkColorScheme(
    primary = AntiFlashWhite,
    onPrimary = VampireBlack,
    background = VampireBlack,
    onBackground = AntiFlashWhite,
    surface = ChineseBlack,
    onSurface = AntiFlashWhite,
    secondary = LightGray,
    onSecondary = VampireBlack,
    tertiary = X11Gray,
    onTertiary = VampireBlack
)

val LightColorScheme = lightColorScheme(
    primary = VampireBlack,
    onPrimary = AntiFlashWhite,
    background = AntiFlashWhite,
    onBackground = VampireBlack,
    surface = LightGray,
    onSurface = VampireBlack,
    secondary = X11Gray,
    onSecondary = VampireBlack,
    tertiary = X11DarkGray,
    onTertiary = VampireBlack
)

@Composable
fun GrailedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val dynamicColorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        dynamicLightColorScheme(context)
    } else null

    val colorScheme = dynamicColorScheme ?: LightColorScheme

    // Используем systemUiController для управления цветом системных баров
    val systemUiController = rememberSystemUiController()

    // Если вам нужен черный контент (значки/текст) на системных барах,
    // вы должны задать цвет системных баров светлым, чтобы иконки были черными.
    // Например, сделаем статус-бар белым, а иконки и текст - темными.
    SideEffect {
        // Установим цвет статус-бара
        systemUiController.setStatusBarColor(
            color = colorScheme.background,
            darkIcons = true
        )

        // Установим цвет навигационной панели
        systemUiController.setNavigationBarColor(
            color = colorScheme.secondary.copy(alpha = 0.4f),
            darkIcons = true
        )
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


