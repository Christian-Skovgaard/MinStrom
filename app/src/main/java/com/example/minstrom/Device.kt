package com.example.minstrom

import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class Device(
    var id:String = "",     //id er var fordi den bliver tilføjet efter classen er blevet initialized i db-kald
    var name:String = "unnamed",

    //tidsrummet hvor brugeren vil have sit device til at starte
    //i string form skrives det som 15:40
    var userStartTime:LocalTime = LocalTime.of(0,0),
    var userStopTime:LocalTime = LocalTime.of(0,0),

    //liste over brugere som for notefikation om planen
    //listen gemmer userId som string
    var associatedUsers:MutableList<String> = mutableListOf(),

    var notificationEnable:Boolean = false,
    //vi sender og får dem i string, det bliver converted til "30m" fx
    //notificationTimeBefore er kun talt i minutter
    var notificationTimeBefore: Duration = 30.minutes,

    var note:String = "",

    //gentagelser er det problem til en anden dag
    //man kunne med fordel bruge Local.Date.of().dayOfWeek
) {
    val calculatedStartTime:LocalTime = LocalTime.of(0,0)

    fun getDeviceTransferClass ():DeviceTransferClass {

        return DeviceTransferClass(
            id = id,
            name = name,
            userStartTime = userStartTime.toString(),
            userStopTime = userStopTime.toString(),
            associatedUsers = associatedUsers.joinToString(),
            notificationEnable = notificationEnable.toString(),
            notificationTimeBefore = notificationTimeBefore.toString(),
            note = note
        )
    }

    fun importDeviceTransferClass (transferObj:DeviceTransferClass) {
        id = transferObj.id
        name = transferObj.name
        userStartTime = LocalTime.parse(transferObj.userStartTime)
        userStopTime = LocalTime.parse(transferObj.userStopTime)
        associatedUsers = transferObj.associatedUsers.split(",").toMutableList()
        notificationEnable = transferObj.notificationEnable.toBoolean()
        notificationTimeBefore = transferObj.notificationTimeBefore.toLong().toDuration(DurationUnit.MINUTES)
        note = transferObj.note
    }

    override fun toString(): String {
        return "Device(id='$id', name='$name', userStartTime=$userStartTime, userStopTime=$userStopTime, associatedUsers=$associatedUsers, notificationEnable=$notificationEnable, notificationTimeBefore=$notificationTimeBefore, note='$note', calculatedStartTime=$calculatedStartTime, img=$img)"
    }
    /*

    //  --setters og getters--

    //userTime
    fun setUserStartTimeFromString (time: String) {userStartTime = LocalTime.parse(time)}
    fun getUserStartTimeAsString ():String {return userStartTime.toString()}

    fun setUserStopTimeFromString (time: String) {userStopTime = LocalTime.parse(time)}
    fun getUserStopTimeAsString ():String {return userStopTime.toString()}

    //NotificationTimeBefore
    fun setNotificationTimeBeforeFromString (string: String) {
        notificationTimeBefore = string.toLong().toDuration(DurationUnit.MINUTES)
    }
    fun getNotificationTimeBeforeAsString ():String {
        return notificationTimeBefore.toString()
    }


     */
    //for billede
    val img: Int? = null
}