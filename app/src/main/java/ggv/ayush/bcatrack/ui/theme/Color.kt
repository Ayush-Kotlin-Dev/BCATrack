package ggv.ayush.bcatrack.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGrey = Color(0xFFE0E0E0)
val DarkGrey = Color(0xFF333333)
val StarColor = Color(0xFFFFD700)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val Colors.titleColor: Color
    @Composable
    get() = if (isLight) DarkGrey else LightGrey

val Colors.welcomeScreenBackgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.statusBarColor
    @Composable
    get() = if (isLight) Purple700 else Color.Black

//checkbox color for light and dark theme
val Colors.checkboxColor: Color
    @Composable
    get() = if (isLight) Purple500 else Color.White