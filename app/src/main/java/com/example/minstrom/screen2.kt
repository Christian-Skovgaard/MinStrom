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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.minstrom.screen1elements.TextOnPage
import com.google.firebase.Firebase
import kotlinx.coroutines.launch

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
        ButtonSelection()
        ConfirmationButton (navController)
    }
}
    }

@Composable
fun ButtonSelection () {
    Column {
        //vi knapperne en overskrift + composables med indhold til bottomSheet
        SettingButton("Indtil notifikation", { BottomSheetNotifikation() })
        SettingButton("Tilføj brugere", { BottomSheetTilføjBruger() })
        SettingButton("Tilføj til kalender", { Text("Det her har vi ikke lavet:)") })
        SettingButton("Tilføj note", { BottomSheetNote() })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingButton(
    text: String,
    //img: Int,
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
            Image(painter = painterResource(R.drawable.imgtilfojnote), //man skal kunne vælge...
                //måske med noget ala
                //age(painter = painterResource(id = selectedImage.value)
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
fun BottomSheetNotifikation() {
    Column(
        modifier = Modifier
            .padding(8.dp)

    ) {
        var notifikation by remember { mutableStateOf("") } //?

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
        Spacer(modifier = Modifier.height(15.dp))
        TextOnPage("Tilføj ansvarlige brugere", 15)
        //hent og vis brugere fra database
        //boks -> if clicked r nogen valgt? som toggle
        //gem det i database
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

    //skal kalde en funktion i viewmodel hvor der ligger state til alle sagerne.
}