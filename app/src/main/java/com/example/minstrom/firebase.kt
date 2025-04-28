package com.example.minstrom

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

data class TestObj (
    val name:String = "",
    val notificationEnable:Boolean = false,
    var id:String = ""
)

class Firebase () {

    val db = Firebase.firestore     //connection detaljer ligger i app/google-services.json

    val deviceCollection = db.collection("devices")

    fun getDeviceList ():List<TestObj> {
        val returnList = mutableListOf<TestObj>()

        deviceCollection.get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        Log.d("DBCall", "DocumentSnapshot data: ${document.data}")
                        val device = document.toObject<TestObj>()   //toObject() virker kun hvis class'en har default values
                        device.id = document.id
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

    fun setDeviceDate (device:Device) {
        deviceCollection.document()
    }
}

