package com.example.minstrom

import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class Device(
    var name:String = "unnamed",

    //tidsrummet hvor brugeren vil have sit device til at starte
    var userStartTime:LocalTime = LocalTime.of(0,0),
    var userStopTime:LocalTime = LocalTime.of(0,0),

    //liste over brugere som for notefikation om planen
    var associatedUsers:MutableList<User> = mutableListOf(),

    var noteficationEnable:Boolean = false,
    var notificationTimeBefore: Duration = 30.minutes

    //gentagelser er det problem til en anden dag
    //man kunne med fordel bruge Local.Date.of().dayOfWeek
) {
    val calculatedStartTime:LocalTime = LocalTime.of(0,0)
}