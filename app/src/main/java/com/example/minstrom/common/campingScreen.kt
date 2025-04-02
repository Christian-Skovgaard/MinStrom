package com.example.minstrom.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun CampingScreen (navController: NavController) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🏕️",
            fontSize = 250.sp
        )
    }
    Button(onClick = {navController.navigate("emoji-screen")
    }) {
        Text("Go to fun page!")
    }
}