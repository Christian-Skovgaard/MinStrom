package com.example.minstrom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.minstrom.screen1elements.BlueButton
import com.example.minstrom.screen1elements.MenuButton
import com.example.minstrom.screen1elements.TaskBox
import com.example.minstrom.screen1elements.TextOnPage

@Composable
fun Screen1(
    navController: NavController,
    deviceList:List<Device>
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
            MenuButton(navController)
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

            TextOnPage("Dine apparater", 18)

            //list of tasks
            /*
            TaskBox(
                "Opvaskemaskine",
                navController,
                img = painterResource(R.drawable.imgvaskemaskine))
            TaskBox(
                "Vaskemaskemaskine",
                navController,
                img = painterResource(R.drawable.imgopvask))
            */

            //henter devices og laver en flot box til hver af dem
           if (deviceList.isNotEmpty()) {
               for(device in deviceList) {
                   TaskBox(
                       title = device.name,
                       navController = navController,
                        img = device.img ?: R.drawable.imgvaskemaskine//hvis du printer den finder du det ID som ku komme i firebase
                   )
               }
           }




            BlueButton("Tilføj plan", navController)
        }
    }
}