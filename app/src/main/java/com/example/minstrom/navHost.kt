package com.example.minstrom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.Screen1


@Composable
fun Navhost(navController: NavHostController) {
    val Screen2ViewModel =  Screen2ViewModel("KjBSQ5HXcBAhcGZZtrcC")
    NavHost(navController = navController, startDestination = "screen-2") {
        composable("Screen-1") {
            Screen1(navController)
        }
        composable("screen-2") {
            Screen2(navController,"KjBSQ5HXcBAhcGZZtrcC")
        }
    }
}

