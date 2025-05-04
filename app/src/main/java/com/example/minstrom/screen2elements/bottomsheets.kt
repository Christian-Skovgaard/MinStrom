package com.example.minstrom.screen2elements

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minstrom.appViewModels.AppViewModel
import com.example.minstrom.data.user.UserViewModel
import com.example.minstrom.screen1elements.TextOnPage
import kotlinx.datetime.LocalTime
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerComponent.WheelTimePicker
import network.chaintech.kmp_date_time_picker.utils.MAX
import network.chaintech.kmp_date_time_picker.utils.MIN
import network.chaintech.kmp_date_time_picker.utils.TimeFormat

@Composable
fun BottomSheetNotifikation(appViewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Notification")
                Switch(
                    checked = appViewModel.selectedDevice.notificationEnable,
                    onCheckedChange = {
                        appViewModel.selectedDevice.notificationEnable = !appViewModel.selectedDevice.notificationEnable
                        Log.d("DeviceUpdate",appViewModel.selectedDevice.notificationEnable.toString())
                    },
                    enabled = true,
                )
            }
            WheelTimePicker(
                title = "",
                doneLabel = "Gem",
                startTime = appViewModel.selectedDevice.notificationTimeBeforeToLocalTime(appViewModel.selectedDevice.notificationTimeBefore),
                minTime = kotlinx.datetime.LocalTime.MIN(),
                maxTime = kotlinx.datetime.LocalTime.MAX(),
                timeFormat = TimeFormat.HOUR_24,
                height = 200.dp,
                rowCount = 3,
                onTimeChangeListener = {snappedDate: LocalTime -> Log.d("VeryVisableTag",snappedDate.toString())},   //Det her virker ikke, så man skal trykke gem
                onDoneClick = {
                    Log.d("VeryVisableTag","timetravel! $it")       //it er den eneste parameter vi for ind, som er snappedData, som representere den tid pickeren er på¨når der bliver trykket done
                    val timeInDuration = appViewModel.selectedDevice.notificationTimeBeforeToDuration(it)
                    appViewModel.selectedDevice.notificationTimeBefore = timeInDuration
                }
            )
        }

    }
}

//tilføj bruger bottom sheet
@Composable
fun BottomSheetTilføjBruger(
    appViewModel: AppViewModel,
    userViewModel: UserViewModel
) {
    val users = userViewModel.userList

    //variablel til at gemme associaed users i men de er kun id
    val associatedUsers = appViewModel.selectedDevice.associatedUsers

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        TextOnPage("Rediger ansvarlige brugere for ${appViewModel.selectedDevice.name}", 20)

        //starter coroutine til at hente users fra database - men kun en når composablen blir komponeret first time
        LaunchedEffect(Unit) {
            userViewModel.getFromDatabase()
        }
        LazyColumn {
            items(users.size) { index ->
                val user = users[index]
                var isChecked by remember {
                    //mutableStateOf(false)
                    mutableStateOf(associatedUsers.contains(user.id))
                }
                Row {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {checkedStatus ->
                            isChecked = checkedStatus
                            //Sørger for at lægge user.id ind i associatedUsers når de er tjekket
                            if (checkedStatus) {
                                if (!associatedUsers.contains(user.id)) {
                                    associatedUsers.add(user.id.toString())
                                }
                            } else {
                                associatedUsers.remove(user.id)
                            }
                        }
                    )
                    TextOnPage("${user.name}", 20)
                }
            }
        }

    }
}

@Composable
fun BottomSheetNote(appViewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        //de her er i viewmodel nu og skal hentes derfra
        var noteText by remember { mutableStateOf("") }

        TextOnPage("Tilføj note", 15)
        Spacer(modifier = Modifier.height(15.dp))
        // Title input
        TextField(
            value = appViewModel.selectedDevice.note,
            label = { Text("Tilføj note her") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { /*viewmodel.*/noteText = it }
        )

        //noteText skal i database når færdig trykkes
    }
}



@Composable
fun ConfirmationButton (
    navController: NavController
) {
    Button(
        onClick = fun() {
            navController.navigate("screen-1") //navigerer til screen1
            //og sender data til viewmodel + databas
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(10.dp)
    ) {
        Text(
            text = "Færdig",
            fontSize = 25.sp
        )
    }
//burde lidt kalde en funktion i viewmodel hvor der ligger state til alle sagerne?
}