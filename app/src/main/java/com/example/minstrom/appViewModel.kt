package com.example.minstrom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalTime

class AppViewModel:ViewModel() {
    var isLoading = true
    var deviceList: List<Device> by mutableStateOf(listOf(
        Device(
            id = "1",
            name = "vaskemaskine",
            imgId = 2130968612,
            userStartTime = LocalTime.of(4, 30),
            userStopTime = LocalTime.of(5,0)
        ),
        Device(
            id = "2",
            name = "opvaskemaskine",
            imgId = 2130968609,
            userStartTime = LocalTime.of(5, 15),
            userStopTime = LocalTime.of(6,0)
        )
    ))
    val firebase = Firebase()
    /*
    init {
        viewModelScope.launch {
            deviceList = firebase.getDeviceList()
            isLoading = false
        }
    }

     */
    var selectedDevice:Device by mutableStateOf(Device())

    fun updateSelectedDevice(device: Device) {
        selectedDevice = device
    }
}