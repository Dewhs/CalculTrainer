package com.example.calcultrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calcultrainer.ui.ChillPage
import com.example.calcultrainer.ui.HomePage
import com.example.calcultrainer.ui.InifitePage
import com.example.calcultrainer.ui.MultiplayerPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home"){
                //composable("home") { HomePage(navigateToCalculPage = {navController.navigate("calculPage")})}
                composable("home") { HomePage(navController)}
                composable("chillPage") {ChillPage(navController)}
                composable("infinitePage") { InifitePage(navController) }
                composable("multiplayerPage") { MultiplayerPage(navController) }
            }
        }
    }
}

