package com.example.minstrom

data class DeviceTransferClass (
    var id:String = "",     //id er var fordi den bliver overwritten efter obj bliver lavet som verify-check
    val name:String = "unnamed",
    val userStartTime:String = "00:00",
    val userStopTime:String = "00:00",
    val associatedUsers:String = "",
    val notificationEnable:String = "false",
    val notificationTimeBefore:String = "",
    val note:String = ""
    )