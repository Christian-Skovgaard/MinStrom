package com.example.minstrom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController


@Composable
fun menuButton(
    navController: NavController
){
    //virkelig questianble og ikke virkende hjemmelavet knap...
    Button( onClick = {/*navController.menuScreen()*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
        ){
            Text("_", color = Color.Black)
            Text("_", color = Color.Black)
            Text("_", color = Color.Black)
        }
    }
}

//overskrifter og tekst på app
@Composable
fun TextOnPage(textInput: String, fontSizeInput: Int) {
    Text(
        text = textInput,
        color = Color.Black,
        fontSize = fontSizeInput.sp,
        fontWeight = FontWeight.Bold
    )
}
//how does tekst work?
//Text(text = title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)

//box til opgaver screen1
@Composable
fun TaskBox(title: String) {
    Box(
        modifier = Modifier
            .size(350.dp, 80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clickable { println("$title clicked") }
            .padding(15.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Row {
            TextOnPage("...", 15)

            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                TextOnPage("Kl noget", 15)
                }
            TextOnPage("...", 15) //knap til screen2??
        }
    }
}


@Composable
fun Screen1(
    navController: NavController
) {
    val hexadecimal = "#E9EFEC" // baggrundsfarven fra figma prototype
    val color = Color(hexadecimal.toColorInt())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp), // Tilføjer afstand mellem elementerne
                Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextOnPage("Planlæg", 30)

            TaskBox("Opvaskemaskine")
            TaskBox("Opvaskemaskine")
            menuButton(navController)
        }
    }
}