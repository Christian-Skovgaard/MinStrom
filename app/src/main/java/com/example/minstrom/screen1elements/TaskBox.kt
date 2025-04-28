package com.example.minstrom.screen1elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.minstrom.R

//box til opgaver screen1
@Composable
fun TaskBox(
        title: String,
        navController: NavController,
        img: Painter
) {
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
            //ikon
            Image( painter = img,
                  contentDescription = null)

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