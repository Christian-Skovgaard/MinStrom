package com.example.minstrom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel:ViewModel() {
    var isLoading = true
    var deviceList: List<Device> by mutableStateOf(listOf(
        Device(
            id = "26",
            name = "Tie-Fighter",
        ),
        Device(
            id = "2",
            name = "El-guitar",
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