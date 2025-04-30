package com.example.minstrom

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.minstrom.screen1elements.TextOnPage
import kotlinx.datetime.LocalTime
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerComponent.WheelTimePicker
import network.chaintech.kmp_date_time_picker.utils.MAX
import network.chaintech.kmp_date_time_picker.utils.MIN
import network.chaintech.kmp_date_time_picker.utils.TimeFormat
import com.google.firebase.Firebase
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Screen2 (navController: NavController,appViewModel:AppViewModel) {
    val hexadecimal = "#E9EFEC" //baggrundsfarve fra figma prototype
    val color = Color(hexadecimal.toColorInt())

    //lav viewmodel
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp), //afstand mellem elementerne
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TextOnPage("Planlægning", 30)
        TextOnPage(appViewModel.selectedDevice.name, 20)
        //prisbox
        //slider
        ButtonSelection(appViewModel = appViewModel)
        ConfirmationButton (navController)
    }
}
    }

@Composable
fun ButtonSelection (appViewModel:AppViewModel) {
    Column {
        //vi knapperne en overskrift + composables med indhold til bottomSheet
        SettingButton(
            "Indtil notifikation",
            img = 2130968606, //bell ikon
            { BottomSheetNotifikation(appViewModel) },
        )
        SettingButton(
            "Tilføj brugere",
            2130968610, //bruger ikon
            { BottomSheetTilføjBruger(appViewModel = appViewModel, userViewModel = UserViewModel())}
        )
        SettingButton(
            "Tilføj til kalender",
            2130968608, //ikon gentag-vibes
            { Text("Her ville man kunne vælge at ${appViewModel.selectedDevice.name} skulle planlægges til at køre f.eks et bestemt antal gange om ugen") }
        )
        SettingButton(
            "Tilføj note",
            2130968611, //note ikon
            { BottomSheetNote() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingButton(
    text: String,
    img: Int,
    bottomSheetIndhold: @Composable ()-> Unit //den modtager en composable med indholdet til bottomsheet
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .clickable(onClick = { showBottomSheet = true }) //OnClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            //image
            Image(painter = painterResource(img),
                contentDescription = null,
                Modifier.size(40.dp))

            Text(
                text = text,
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        Column {
            if (showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    bottomSheetIndhold() //det reele indhold i bottomsheet kaldes her
                }
            }
        }
    }
}


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
                onTimeChangeListener = {snappedDate:LocalTime -> Log.d("VeryVisableTag",snappedDate.toString())},   //Det her virker ikke, så man skal trykke gem
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
fun BottomSheetNote() {
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
            value = noteText,
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
        onClick = fun () {
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
    //burde lidt kalde en funktion i viewmodel hvor der ligger state til alle sagerne.
}
