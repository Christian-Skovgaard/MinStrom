package com.example.minstrom.screen1elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minstrom.AppViewModel
import com.example.minstrom.Device
import com.example.minstrom.R

//box til opgaver screen1
@Composable
fun TaskBox(
        device: Device,
        appViewModel: AppViewModel,
        navController: NavController
) {
    Box(
        modifier = Modifier
            .width(380.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(15.dp)
            .clickable{
                appViewModel.updateSelectedDevice(device)
                navController.navigate("Screen-2")
            }
        ,
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
        ) {
            //ikon
            Image( painter = painterResource(device.imgId),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp) //maksstørrelse
                    .padding(5.dp)
            )

            Column {
                Text(
                    text = device.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                TextOnPage("Planlagt til kl: ${device.userStartTime} - ${device.userStopTime}", 15)
                TextOnPage("Notifikationer til: ${device.notificationEnable}" , 15)
            }
        }
        //if notifikationer er til så vis billede af klokke?
        if (device.notificationEnable == true) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,horizontalArrangement = Arrangement.End
            ) {
                Image( painter = painterResource(R.drawable.imgbell),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp) //maksstørrelse
                        .padding(5.dp),
                )
            }
        }
    }
}