package com.example.minstrom.screen1elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController

//planlæg knap nederst - skal ikke virke i prototypen
@Composable
fun BlueButton(
    buttonTekst: String,
    navController: NavController
) {
    val color = Color("#0A7EFD".toColorInt()) //min strøm blå farve
    Button( onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            buttonTekst,
            color = Color.White,
            fontSize = 25.sp
        )
    }
}