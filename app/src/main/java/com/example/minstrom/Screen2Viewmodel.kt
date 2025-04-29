package com.example.minstrom
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class Screen2Viewmodel: ViewModel() {
    var noteText = { mutableStateOf("") }
}