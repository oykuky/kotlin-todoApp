package com.example.kotlin_todoapp.ui.theme

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import com.example.kotlin_todoapp.R


// Renklerin tanımlandığı XML dosyasını yükleyin
val res = Resources.getSystem()
val pinkColor = Color(res.getColor(R.color.pink))
val lightPinkColor = Color(res.getColor(R.color.light_pink))
val purpleColor = Color(res.getColor(R.color.purple))
val blackColor = Color(res.getColor(R.color.black))
val lightBlackColor = Color(res.getColor(R.color.light_black))
val whiteColor = Color(res.getColor(R.color.white))
val grayColor = Color(res.getColor(R.color.light_gray))

private val DarkColorScheme = darkColorScheme(
    primary = lightPinkColor,
    secondary = pinkColor,
    tertiary = blackColor,
    background = whiteColor,
    outline = purpleColor,
    onPrimary = grayColor,
)

private val LightColorScheme = lightColorScheme(
    primary = lightPinkColor,
    secondary = pinkColor,
    tertiary = blackColor,
    background = whiteColor,
    outline = purpleColor,
    onPrimary = grayColor,

)

@Composable
fun KotlintodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}