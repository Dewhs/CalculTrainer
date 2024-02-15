package com.example.calcultrainer.ui

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calcultrainer.ui.theme.Multiplayer

@Composable
fun MultiplayerPage(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicText(text = "The Multiplayer page comming soon !")
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Multiplayer),
            onClick = {navController.popBackStack()}) {
            BasicText(text = "Back to Home")
        }
    }
}
