package com.example.minstrom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minstrom.appViewModels.AppViewModel
import com.example.minstrom.screen1elements.Screen1
import com.example.minstrom.screen2elements.Screen2


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

