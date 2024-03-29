package com.example.calcultrainer.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.calcultrainer.R


val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Heading1 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Dark
)
val Heading2 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Dark
)

val ResultStyle = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Dark,
    textAlign = TextAlign.Center
)


val Heading3 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Dark
)

val false_msgHistorique_H = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Infinite_Dark
)

val true_msgHistorique_H = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Chill_Dark
)

val Heading4 = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Dark
)


val NavBarItemLabelStyle = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    //color = Dark
)







// Set of Material typography styles to start with
/*val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)*/