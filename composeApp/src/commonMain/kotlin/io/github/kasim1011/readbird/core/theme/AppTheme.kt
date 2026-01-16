package io.github.kasim1011.readbird.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color definitions
val md_theme_dark_primary = Color(0xFF1DA1F2)
val md_theme_dark_secondary = Color(0xFF03DAC6)
val md_theme_dark_tertiary = Color(0xFFBB86FC)
val md_theme_dark_background = Color(0xFF000000)
val md_theme_dark_surface = Color(0xFF121212)
val md_theme_dark_onPrimary = Color(0xFF000000)
val md_theme_dark_onSecondary = Color(0xFF000000)
val md_theme_dark_onTertiary = Color(0xFF000000)
val md_theme_dark_onBackground = Color(0xFFE0E0E0)
val md_theme_dark_onSurface = Color(0xFFE0E0E0)
val md_theme_dark_outline = Color(0xFF2A2A2A)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    tertiary = md_theme_dark_tertiary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    onPrimary = md_theme_dark_onPrimary,
    onSecondary = md_theme_dark_onSecondary,
    onTertiary = md_theme_dark_onTertiary,
    onBackground = md_theme_dark_onBackground,
    onSurface = md_theme_dark_onSurface,
    outline = md_theme_dark_outline,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
