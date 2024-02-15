package com.example.calcultrainer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calcultrainer.ui.theme.Heading2
import com.example.calcultrainer.ui.theme.Infinite
import com.example.calcultrainer.ui.theme.Infinite_Dark

@Composable
fun InifitePage(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "The Infinite page comming soon !", style = Heading2)
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Infinite),
            onClick = {navController.popBackStack()}) {
            Text(text = "Back to Home", style = Heading2, color = Infinite_Dark)
        }
    }
}