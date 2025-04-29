package com.example.minstrom

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

data class TestObj (
    val name:String = "",
    val notificationEnable:Boolean = false,
    var id:String = ""
)

class Firebase () {
    val db = Firebase.firestore     //connection detaljer ligger i app/google-services.json
    val deviceCollection = db.collection("devices")

    fun getDeviceList ():List<Device> {
        val returnList = mutableListOf<Device>()
        deviceCollection.get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        Log.d("DBCall", "DocumentSnapshot data: ${document.data}")
                        val deviceTransferObj = document.toObject<DeviceTransferClass>()   //toObject() virker kun hvis class'en har default values
                        deviceTransferObj.id = document.id
                        Log.d("DBCall", "DeviceTransferObj: ${deviceTransferObj}")

                        val device = Device()
                        device.importDeviceTransferClass(deviceTransferObj)
                        returnList.add(device)
                        Log.d("DBCall", "Device: ${device}")
                    }

                    //Log.d("Vask", "DocumentSnapshot data: ${document}")
                } else {
                    Log.d("DBCall", "List is empty")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("DBCall", "get failed with ", exception)
            }

        return returnList.toList()
    }

    suspend fun getDevice (deviceId:String): Device? {
        val device = Device()

        val deviceTransferObj =  deviceCollection.document(deviceId).get().await().toObject<DeviceTransferClass>()

        if (deviceTransferObj != null) {
            device.importDeviceTransferClass(deviceTransferObj)
            return device
        } else {
            return null}
    }

    fun setDeviceDate (device:Device) {
        val document = deviceCollection.document(device.id).set(device)
    }


}
