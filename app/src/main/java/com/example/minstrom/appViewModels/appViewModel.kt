package com.example.minstrom.appViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minstrom.data.device.Device
import com.example.minstrom.data.device.Firebase
import java.time.LocalTime

class AppViewModel:ViewModel() {
    var isLoading = true
    var deviceList: List<Device> by mutableStateOf(listOf(
        //dummy data:)
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
    var selectedDevice: Device by mutableStateOf(Device())

    fun updateSelectedDevice(device: Device) {
        selectedDevice = device
    }
}