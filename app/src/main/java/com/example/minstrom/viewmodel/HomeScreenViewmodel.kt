package com.example.minstrom.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeScreenViewmodel: ViewModel() {
    var userInput by mutableStateOf("")

    fun updateTextField(input: String) {
        userInput = input
    }
}