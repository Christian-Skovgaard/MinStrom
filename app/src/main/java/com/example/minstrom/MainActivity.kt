package com.example.minstrom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.minstrom.screen1elements.TaskBox
import com.example.minstrom.ui.theme.MinstromTheme
import com.google.firebase.Firebase
import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel:AppViewModel = AppViewModel()
            val navController = rememberNavController()
            Navhost(navController = navController,appViewModel = appViewModel)

        println(R.drawable.imgvaskemaskine)
            println(R.drawable.imgopvask)

        }
    }
}

//Henter alle users og printer dem (i system.out)
@Composable
fun GetUsers() {
    val userViewModel: UserViewModel = viewModel()
    //kalder getFromDatabase fra instandensen herover
    userViewModel.getFromDatabase()
}

/*
fun firebasetesting () {
    val firebase: Firebase = Firebase()
    firebase.getDeviceList()

    val deviceTransferObj = DeviceTransferClass(
        id = "25",
        name = "Darth Vader",
        userStartTime = "14:30",
        userStopTime = "18:26",
        associatedUsers = "01,45",
        notificationEnable = "true",
        notificationTimeBefore = "50",
        note = "Jeg elsker lagkage"
    )

    val device = Device()

    device.importDeviceTransferClass(deviceTransferObj)

    Log.d("VERYVISABLETAG",device.toString())

}
*/

fun testTime () {
    var time: LocalTime = LocalTime.of(15,30)

    var string = "16:37"
    var newTime = LocalTime.parse(string)

    Log.d("VERYVISABLETAG",newTime.toString())
}

fun listTesting () {
    val list = mutableListOf(User("Indieana Jones","26"))
    list.add(User("Jesus","1"))
    val string = list.joinToString()
    val newList = string.split(",")

    Log.d("VERYVISABLETAG", newList.joinToString())
}

fun timeConvertionTesting ():LocalTime {
    val duration:Duration = 630.minutes
    val string = duration.toString()
    val stringList = string.split(" ")
    if (stringList.size == 1) {
        val totalMin = stringList[0].replace("m","").toInt()
        return LocalTime.of(0,totalMin)
    } else {
        val totalHour = stringList[0].replace("h","").toInt()
        val totalMin = stringList[1].replace("m","").toInt()
        return LocalTime.of(totalHour,totalMin)
    }
}