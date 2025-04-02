package com.example.minstrom

import EmojiScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.common.CampingScreen

@Composable
fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "start-screen") {
                composable("start-screen") {
                       ScreenLinea(navController)
                }

                composable("camping-screen") {
                        CampingScreen(navController)
                }

                composable("emoji-screen") {
                        EmojiScreen(navController)
                }
        }
}