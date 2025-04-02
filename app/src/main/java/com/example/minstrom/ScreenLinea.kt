package com.example.minstrom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.minstrom.common.CampingScreen

@Composable
fun ScreenLinea(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Color(0xFF9E82F0))
            .fillMaxSize()
    ) {
        Button(onClick = { navController.navigate("camping-screen")
        }) {
            Text("Go to camp")
        }
    }
}