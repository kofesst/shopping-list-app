package me.kofesst.android.shoppinglist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.kofesst.android.shoppinglist.R

val AppFontFamily = FontFamily(
    // Normal
    Font(R.font.exo2_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.exo2_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.exo2_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.exo2_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.exo2_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.exo2_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.exo2_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.exo2_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.exo2_black, FontWeight.Black, FontStyle.Normal),

    // Italic
    Font(R.font.exo2_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.exo2_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.exo2_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.exo2_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.exo2_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.exo2_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.exo2_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.exo2_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.exo2_black_italic, FontWeight.Black, FontStyle.Italic)
)

val AppTypography = Typography(
    labelLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp,
        lineHeight = 16.sp,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp,
        lineHeight = 16.sp,
        fontSize = 11.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.1.sp,
        lineHeight = 16.sp,
        fontSize = 12.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 40.sp,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 36.sp,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 32.sp,
        fontSize = 24.sp
    ),
    displayLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 64.sp,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 52.sp,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 44.sp,
        fontSize = 36.sp
    ),
    titleLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.W400,
        letterSpacing = 0.sp,
        lineHeight = 28.sp,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),
)