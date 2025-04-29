package com.example.minstrom

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AppViewModel:ViewModel() {
    var deviceList: List<Device> by mutableStateOf(listOf())
    val firebase = Firebase()
    init {
        viewModelScope.launch {
            deviceList = firebase.getDeviceList()
        }
    }
}