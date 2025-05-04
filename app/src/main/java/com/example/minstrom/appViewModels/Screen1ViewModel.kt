package com.example.minstrom.appViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minstrom.data.device.Device

class Screen1ViewModel:ViewModel() {
    var deviceList: List<Device> by mutableStateOf(listOf())

}
//vi endte med kun at have en viewmodel til hele appen - og en til user-data
