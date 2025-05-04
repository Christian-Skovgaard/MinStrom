package com.example.minstrom.screen2elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.minstrom.appViewModels.AppViewModel
import com.example.minstrom.data.user.UserViewModel
import com.example.minstrom.screen1elements.TextOnPage

@Composable
fun Screen2 (navController: NavController,appViewModel: AppViewModel) {
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
fun ButtonSelection (appViewModel: AppViewModel) {
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
            { BottomSheetTilføjBruger(appViewModel = appViewModel, userViewModel = UserViewModel()) }
        )
        SettingButton(
            "Tilføj til kalender",
            2130968608, //ikon gentag-vibes
            { Text("Her ville man kunne vælge at ${appViewModel.selectedDevice.name} skulle planlægges til at køre f.eks et bestemt antal gange om ugen") }
        )
        SettingButton(
            "Tilføj note",
            2130968611, //note ikon
            { BottomSheetNote(appViewModel) }
        )
    }
}

