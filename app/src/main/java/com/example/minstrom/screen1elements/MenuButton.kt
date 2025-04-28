package com.example.minstrom.screen1elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.minstrom.R


@Composable
fun MenuButton(
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