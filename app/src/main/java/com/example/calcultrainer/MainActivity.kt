package com.example.calcultrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calcultrainer.ui.CalculPage
import com.example.calcultrainer.ui.HomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home"){
                composable("home") { HomePage(navigateToCalculPage = {navController.navigate("calculPage")})}
                //composable("home") { HomePage(navController)
                composable("calculPage") {CalculPage(navController)}
            }
        }
    }
}

