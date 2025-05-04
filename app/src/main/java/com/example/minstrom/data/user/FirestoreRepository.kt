package com.example.minstrom.data.user

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    //user
    val db = Firebase.firestore
    //get - ville kunne printe dem, gemme dem i viewmodel, skrive på siden
    //asynkron - skal kaldes i coroutine
    suspend fun get(): List<User> {
        return db.collection("users")
            .get()
            .await()
            .toObjects(User::class.java)
    }

    //og en til at få en bestemt user udfra id??
        suspend fun getOne(id: String):String {
            val foundUser = db.collection("users")
                .document(id)
                .get()
                .await()
                .toObject(User::class.java)
        if (foundUser!=null) {
            return foundUser.name
        } else return ""
        }
}