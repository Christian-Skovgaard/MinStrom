package com.example.minstrom.data.device

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class Device(
    id:String = "",     //id er var fordi den bliver tilføjet efter classen er blevet initialized i db-kald
    name:String = "unnamed",
    userStartTime:LocalTime = LocalTime.of(0,0),
    userStopTime:LocalTime = LocalTime.of(0,0),
    associatedUsers:MutableList<String> = mutableListOf(),
    notificationEnable:Boolean = false,
    notificationTimeBefore: Duration = 30.minutes,
    note:String = "",
    imgId: Int = 0,
) {
    var id:String by mutableStateOf(id)     //id er var fordi den bliver tilføjet efter classen er blevet initialized i db-kald
    var name:String by mutableStateOf(name)

    //tidsrummet hvor brugeren vil have sit device til at starte
    //i string form skrives det som 15:40
    var userStartTime:LocalTime by mutableStateOf(userStartTime)
    var userStopTime:LocalTime by mutableStateOf(userStopTime)

    //liste over brugere som for notefikation om planen
    //listen gemmer userId som string
    var associatedUsers:MutableList<String> = mutableStateListOf(*associatedUsers.toTypedArray()) //this makses no senseeee mmhmayeah


    var notificationEnable:Boolean by mutableStateOf(notificationEnable)
    //vi sender og får dem i string, det bliver converted til "30m" fx
    //notificationTimeBefore er kun talt i minutter
    var notificationTimeBefore: Duration by mutableStateOf(notificationTimeBefore)

    var note:String by mutableStateOf(note)
    var imgId: Int by mutableStateOf(imgId)

    //gentagelser er det problem til en anden dag
    //man kunne med fordel bruge Local.Date.of().dayOfWeek

    //Det her er variablen som skulle have gemt den til som maskinen skulle starte ud fra API'et
    val calculatedStartTime:LocalTime = LocalTime.of(0,0)


    //Det her med DeviceTransferClass er en virklig dum idé, og ville bare kunne gøre nemmere og bedre med constructoren. Den er lavet som
    //lappeløsning fordi vores FireStore havde svært ved komplekse datatyper og vi har derfor omdannet alt til strings før
    //vi sender det til DB. Det vil sige at vores DeviceTransferClass effektivt er et mellemlag mellem den rigtige Device-class
    //og vores DB. Den er også lavet på et tidspunkt hvor vi havde vores variabler med komplekse datatyper i constructoren og
    //har derfor været nødvendigt som et "pre-step" til den.
    fun getDeviceTransferClass (): DeviceTransferClass {
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

    fun importDeviceTransferClass (transferObj: DeviceTransferClass) {
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
        return "Device(id='$id', name='$name', userStartTime=$userStartTime, userStopTime=$userStopTime, associatedUsers=$associatedUsers, notificationEnable=$notificationEnable, notificationTimeBefore=$notificationTimeBefore, note='$note', calculatedStartTime=$calculatedStartTime, imgId=$imgId)"
    }

    //de her to funktioner er til fordi vi har gemt vores notificationTimeBefore som Duration, men det library vi har brugt
    //til at kunne indsætte tiden tror det er et ur, og derfor giver LocalTime som variabel i steddet, derfor har vi de er to
    //convertion funktioner til at regne frem og tilbage. Det er lidt dumt, oh well
    fun notificationTimeBeforeToLocalTime (duration: Duration):kotlinx.datetime.LocalTime {
        val stringList = duration.toString().split(" ")
        if (stringList.size == 1) {
            val totalMin = stringList[0].replace("m","").toInt()
            return kotlinx.datetime.LocalTime(0,totalMin)
        } else {
            val totalHour = stringList[0].replace("h","").toInt()
            val totalMin = stringList[1].replace("m","").toInt()
            return kotlinx.datetime.LocalTime(totalHour,totalMin)
        }
    }
    fun notificationTimeBeforeToDuration (time: kotlinx.datetime.LocalTime):Duration {
        val stringList = time.toString().split(":")
        val totalMin:Int = stringList[1].toInt() + stringList[0].toInt()*60     //her tager vi og lægger minutter sammen med timer, som bliver ganget med 60 for at omregne til minutter
        return totalMin.toLong().toDuration(DurationUnit.MINUTES)
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

}

