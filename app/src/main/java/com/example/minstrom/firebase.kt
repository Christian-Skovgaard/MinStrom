package com.example.minstrom

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

data class TestObj (
    val name:String = "",
    //val associatedUsers:List<String>,
    val notificationEnable:Boolean = false
)

fun getDeviceList () {

    val db = Firebase.firestore

    val docRef = db.collection("devices")
    //.document("jd9bhcf0iQW8x0zpXCPw")

    docRef.get()
        .addOnSuccessListener { documents ->
            if (documents != null) {
                for (document in documents) {
                    Log.d("Vask", "DocumentSnapshot data: ${document}")
                    val device = document.toObject<TestObj>()
                    Log.d("Vask", "Device: ${device}")
                }

                //Log.d("Vask", "DocumentSnapshot data: ${document}")
            } else {
                Log.d("Vask", "List is empty")
            }
        }
        .addOnFailureListener { exception ->
            Log.d("Vask", "get failed with ", exception)
        }
}

