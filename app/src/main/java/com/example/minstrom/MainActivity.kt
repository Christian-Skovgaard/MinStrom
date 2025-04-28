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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.minstrom.ui.theme.MinstromTheme
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
            listTesting()
            val navController = rememberNavController()
            Navhost(navController = navController)

        }
    }
}

fun firebasetesting () {
    val firebase: Firebase = Firebase()
    firebase.getDeviceList()

    val device = Device("Xa98xLpviJRyHzb00jjV","Sidewinder Mk I",LocalTime.of(15,40))
    firebase.setDeviceDate(device)
}

fun testTime () {
    var time: LocalTime = LocalTime.of(15,30)

    var string = "16:37"
    var newTime = LocalTime.parse(string)

    Log.d("VERYVISABLETAG",newTime.toString())
}

fun listTesting () {
    val list = mutableListOf(User("Indieana Jones","26"))
    list.add(User("Jesus","1"))
    Log.d("VERYVISABLETAG", list.toString())

}