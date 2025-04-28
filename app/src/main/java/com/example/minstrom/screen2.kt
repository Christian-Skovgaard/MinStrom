package com.example.minstrom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class Screen2ViewModel constructor(val device:Device):ViewModel() {

}


@Composable
fun Screen2 ( navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title("Planlægning")
        SubTitle("Vaskemaskine")
        //prisbox
        //slider
        ButtonSelection()
        ConfirmationButton ()
    }
}


@Composable
fun Title (text:String) {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text=text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
    }
}

@Composable
fun SubTitle (text:String) {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text=text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
    }
}



@Composable
fun ButtonSelection () {
    Column {
        SettingButton("Notefikationer")
        SettingButton("Tilføj brugere")
        SettingButton("Kalender")
        SettingButton("Skriv Note")
    }
}


@Composable
fun SettingButton(
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //image
            Text(
                text = text,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun ConfirmationButton () {
    Button(
        onClick = fun () {},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Færdig"
        )
    }
}