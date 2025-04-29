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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.minstrom.screen1elements.TextOnPage
import com.google.firebase.Firebase
import kotlinx.coroutines.launch

class Screen2ViewModel constructor(deviceId:String):ViewModel() {
    var isLoading:Boolean = true
    val firebase = Firebase()
    var device by mutableStateOf(Device())
    init {
        viewModelScope.launch {
            val deviceFromFireStore = firebase.getDevice(deviceId)
            if (deviceFromFireStore != null) {
                device = deviceFromFireStore
            }

            Log.d("visableTag","Device instaciated")
        }
    }
}


@Composable
fun Screen2 (navController: NavController, deviceId: String) {
    val screen2ViewModel =  Screen2ViewModel(deviceId)
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title("Planlægning")
        SubTitle(screen2ViewModel.device.name)
        //prisbox
        //slider
        ButtonSelection()
        ConfirmationButton ()
    }
}


@Composable
fun Title (text:String) {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text=text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
    }
}

@Composable
fun SubTitle (text:String) {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text=text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
    }
}



@Composable
fun ButtonSelection () {
    Column {
        //vi knapperne en overskrift + composables med indhold til bottomSheet
        SettingButton("Notifikationer", { BottomSheetNotifikation() })
        SettingButton("Tilføj brugere", { BottomSheetTilføjBruger() })
        SettingButton("Kalender", { Text("Det her har vi ikke lavet:)") })
        SettingButton("Skriv Note", { BottomSheetNote() })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingButton(
    text: String,
    bottomSheetIndhold: @Composable ()-> Unit //den modtager en composable med indholdet til bottomsheet
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = { showBottomSheet = true }) //OnClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //image
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
fun BottomSheetNotifikation() {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        TextOnPage("Indstil notifikation", 15)
        //sej måde at vælge tid på...
        //by remenber
        //gem det i database
    }
}

@Composable
fun BottomSheetTilføjBruger() {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        TextOnPage("Tilføj ansvarlige brugere", 15)
        //hent og vis brugere fra database
        //boks -> if clicked r nogen valgt? som toggle
        //gem det i database
    }
}

//Linea vil gerne lave tekstfeltet<3
@Composable
fun BottomSheetNote() {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        TextOnPage("Tilføj ansvarlige brugere", 15)
        //hent og vis brugere fra database
        //boks -> if clicked r nogen valgt? som toggle
        //gem det i database
    }
}


@Composable
fun ConfirmationButton () {
    Button(
        onClick = fun () {},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A7EFD)),
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Færdig"
        )
    }
}