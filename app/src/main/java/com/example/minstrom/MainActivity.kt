package com.example.minstrom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.minstrom.appViewModels.AppViewModel
import com.example.minstrom.data.user.User
import com.example.minstrom.data.user.UserViewModel
import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = AppViewModel()
            val navController = rememberNavController()
            Navhost(navController = navController,appViewModel = appViewModel)
        }
    }
}


//kode herunder er gemt for sjov og bliver ikke brugt<3

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