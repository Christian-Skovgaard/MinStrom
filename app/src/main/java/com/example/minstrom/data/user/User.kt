package com.example.minstrom.data.user
import com.google.firebase.firestore.DocumentId


data class User (
    val name:String,
    //val userId:String,
    @DocumentId @Transient val id: String? = null //ignorer ved upload, skal kun bruges til at få doc-id ved modtagelsen
) {
    // Tom konstruktør til Firestore fordi chatGPT siger det
    constructor() : this(null.toString(), null)

    override fun toString(): String {
        return "$name"; "$id"
    }
}

