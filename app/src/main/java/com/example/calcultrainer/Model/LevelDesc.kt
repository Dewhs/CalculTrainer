package com.example.calcultrainer.Model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.calcultrainer.R
import com.example.calcultrainer.ui.theme.Chill
import com.example.calcultrainer.ui.theme.Chill_Dark
import com.example.calcultrainer.ui.theme.Heading2
import com.example.calcultrainer.ui.theme.Infinite
import com.example.calcultrainer.ui.theme.Infinite_Dark
import com.example.calcultrainer.ui.theme.Training
import com.example.calcultrainer.ui.theme.Training_Dark

data class LevelDesc(val name: String, val description: String, val lightColor: Color, val darkColor: Color, val path: Int, val playPath: Int = R.drawable.play, val textStyle : TextStyle = Heading2)


val Levels = listOf(
    LevelDesc(
        name = "Chill",
        description = "The Chill game mode is designed for a short session in a more relaxed format, and without a timer.",
        lightColor = Chill,
        darkColor = Chill_Dark,
        path = R.drawable.flower_icon,
    ),
    LevelDesc(
        name = "Training",
        description = "The Training game mode is designed for personalized train session.",
        lightColor = Training,
        darkColor = Training_Dark,
        path = R.drawable.gym_icon,
    ),
    LevelDesc(
        name = "Infinite",
        description = "The Infinite game mode is designed for a session as difficult as it is long, with calculations going on ad infinitum at an adaptive level and time. Survive to score maximum points",
        lightColor = Infinite,
        darkColor = Infinite_Dark,
        path = R.drawable.flamme_icon,
    ),
)