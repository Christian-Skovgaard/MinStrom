package com.example.minstrom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.Screen1


@Composable
fun Navhost(navController: NavHostController, appViewModel: AppViewModel) {
    NavHost(navController = navController, startDestination = "Screen-1") {
        composable("Screen-1") {
            Screen1(
                navController = navController,
                appViewModel = appViewModel
            )
        }
        composable("Screen-2") {
            Screen2(navController,appViewModel)
        }
    }
}

