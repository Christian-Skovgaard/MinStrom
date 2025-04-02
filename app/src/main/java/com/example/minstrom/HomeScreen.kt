package com.example.minstrom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.minstrom.viewmodel.HomeScreenViewmodel

@Composable
fun HomeScreen(
    navController: NavController
) {
    val homeScreenViewmodel: HomeScreenViewmodel = viewModel()

    Column(
        modifier = Modifier
            .background(Color(0xFF9E82F0))
            .fillMaxSize()
    ) {
        Button(onClick = { navController.navigate("camping-screen")
        }) {
            Text("Go to camp")
        }

        TextField(
            value = homeScreenViewmodel.userInput,
            onValueChange = { userInput -> homeScreenViewmodel.updateTextField(userInput)
            },
            label = {"Write something here..."}
        )
    }
}