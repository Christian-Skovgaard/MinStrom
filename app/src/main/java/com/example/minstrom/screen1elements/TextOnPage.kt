package com.example.minstrom.screen1elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//overskrifter+tekst
@Composable
fun TextOnPage(textInput: String, fontSizeInput: Int) {
    Text(
        text = textInput,
        color = Color.Black,
        fontSize = fontSizeInput.sp,
        fontWeight = FontWeight.Bold
    )
}