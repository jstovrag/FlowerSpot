package com.flower.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val ubuntu = FontFamily(
    Font(R.font.ubuntu_bold),
    Font(R.font.ubuntu_bold_italic),
    Font(R.font.ubuntu_italic),
    Font(R.font.ubuntu_light),
    Font(R.font.ubuntu_light_italic),
    Font(R.font.ubuntu_medium),
    Font(R.font.ubuntu_medium_italic),
    Font(R.font.ubuntu_regular)
)

// Do not use yet until correct values are agreed with design team
val FlowerSpotTypography = Typography(
    defaultFontFamily = ubuntu,
    h1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        letterSpacing = 0.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.25.sp
    )
)
