package com.example.minstrom

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

//ikonerne ligger i res.drawable
    //gad jo godt at man kunne bruge imgName: String i DeviceIconVaskemaskine() som så brugte ${imgName} ...

@Composable
fun DeviceIconVaskemaskine() {
    val img = painterResource(R.drawable.imgvaskemaskine)
    Image(painter = img,
        contentDescription = null)
}

@Composable
fun DeviceIconOpvaskemaskine() {
    val img = painterResource(R.drawable.imgopvask)
    Image(painter = img,
        contentDescription = null)
}
