package com.example.minstrom

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlin.reflect.typeOf

fun getData () {

    val db = Firebase.firestore

    val docRef = db.collection("devices").document("jd9bhcf0iQW8x0zpXCPw")

    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                Log.d("Vask", "DocumentSnapshot data: ${document.data}")
                Log.d("Vask", "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d("Vask", "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d("Vask", "get failed with ", exception)
        }
}



