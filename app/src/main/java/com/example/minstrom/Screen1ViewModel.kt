package com.example.minstrom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Screen1ViewModel:ViewModel() {
    var deviceList: List<Device> by mutableStateOf(listOf())

}
