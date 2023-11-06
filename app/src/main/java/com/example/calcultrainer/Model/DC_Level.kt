package com.example.calcultrainer.Model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.calcultrainer.R
import com.example.calcultrainer.View.theme.Chill
import com.example.calcultrainer.View.theme.Heading2_Btn_Chill
import com.example.calcultrainer.View.theme.Heading2_Btn_Infinite
import com.example.calcultrainer.View.theme.Heading2_Btn_Training
import com.example.calcultrainer.View.theme.Infinite
import com.example.calcultrainer.View.theme.Training

data class DC_Level(val name: String,val Description: String, val color: Color, val path: Int, val play_path: Int, val Heading2_btn : TextStyle)


val Levels = listOf(
    DC_Level(
        name = "Chill",
        Description = "The Chill game mode is designed for a short session in a more relaxed format, and without a timer.",
        color = Chill,
        path = R.drawable.chill_c_icon,
        play_path = R.drawable.play_chill,
        Heading2_btn = Heading2_Btn_Chill
    ),
    DC_Level(
        name = "Training",
        Description = "The Training game mode is designed for personalized train session.",
        color = Training,
        path = R.drawable.training_c_icon,
        play_path = R.drawable.play_training,
        Heading2_btn = Heading2_Btn_Training
    ),
    DC_Level(
        name = "Infinite",
        Description = "The Infinite game mode is designed for a session as difficult as it is long, with calculations going on ad infinitum at an adaptive level and time. Survive to score maximum points",
        color = Infinite,
        path = R.drawable.infinite_c_icon,
        play_path = R.drawable.play_infinite,
        Heading2_btn = Heading2_Btn_Infinite
    ),
)