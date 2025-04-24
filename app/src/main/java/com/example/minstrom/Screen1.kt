package com.example.minstrom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController


//overskrifter og tekst på app
@Composable
fun TextOnPage(textInput: String, fontSizeInput: Int) {
    Text(
        text = textInput,
        color = Color.Black,
        fontSize = fontSizeInput.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize())
}
//how does tekst work?
//Text(text = title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)


@Composable
fun Screen1(
    navController: NavController
) {
    val hexadecimal = "#f5f5f5" // AA: Green, BB: Red, CC: Blue
    val color  = Color(hexadecimal.toColorInt())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(20.dp)
    ) {

        Column {


            TextOnPage("Planlæg", 30)
        }
    }
}