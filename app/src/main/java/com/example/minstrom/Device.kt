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
    var associatedUsers:MutableList<User> = mutableListOf(),

    var noteficationEnable:Boolean = false,
    //vi sender og får dem i string, det bliver converted til "30m" fx
    //notificationTimeBefore er kun talt i minutter
    var notificationTimeBefore: Duration = 30.minutes,

    var note:String = "",

    //gentagelser er det problem til en anden dag
    //man kunne med fordel bruge Local.Date.of().dayOfWeek
) {
    val calculatedStartTime:LocalTime = LocalTime.of(0,0)

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
}