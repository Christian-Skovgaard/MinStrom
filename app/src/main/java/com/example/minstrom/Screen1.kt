package com.example.minstrom

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun menuButton(
    navController: NavController
){
    val color = Color("#E9EFEC".toColorInt()) // baggrundsfarve på siden til bag img
    Button( onClick = {/*navController.menuScreen()*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color),
    ) {
        Image(
                painter = painterResource(R.drawable.imgmenu),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .clip(CircleShape)
        )
    }
}

//planlæg knap nederst - skal ikke virke i prototypen
@Composable
fun blueButton(
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

//box til opgaver screen1
@Composable
fun TaskBox(title: String, navController: NavController) {
    Box(
        modifier = Modifier
            .size(380.dp, 90.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(15.dp)
            .clickable {
                navController.navigate("screen-2") //navigerer til screen2
            },
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            DeviceIconVaskemaskine()

            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                TextOnPage("Kl noget...", 15)
                }
        }
    }
}


@Composable
fun Screen1(
    navController: NavController
) {
    val hexadecimal = "#E9EFEC" //baggrundsfarve fra figma prototype
    val color = Color(hexadecimal.toColorInt())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {

        Row(
            modifier = Modifier
            .fillMaxWidth()
            //.padding(5.dp),
            ,horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.height(15.dp)) //kunne godt tænke mig at den kom lidt længere ned...
            menuButton(navController)
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp), //afstand mellem elementerne
                Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextOnPage("Planlæg", 30)
            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextOnPage("I dag", 18)
                TextOnPage("Uge", 16)
            }

            Image(
                painter = painterResource(R.drawable.strompris),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .width(380.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            //list of tasks
            TaskBox("Opvaskemaskine", navController)
            TaskBox("Opvaskemaskine", navController)

            blueButton("Tilføj plan", navController)
        }
    }
}