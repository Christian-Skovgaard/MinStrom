package com.example.minstrom
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentId
import kotlinx.coroutines.launch

data class User (
    val name:String,
    //val userId:String,
    @DocumentId @Transient val id: String? = null //ignorer ved upload, skal kun bruges til at få doc-id ved modtagelsen
) {
    // Tom konstruktør til Firestore (kræves) fordi chatGPT siger det
    constructor() : this(null.toString(), null)
}

class UserViewModel: ViewModel() {
    val repository = FirestoreRepository()
    //instands af firestoreRespos..

    var userList: List<User> = listOf()

    fun getFromDatabase() {
        viewModelScope.launch {
            var result = repository.get()
            println(result) //bliver nødt til at vente på at den har hentet alle før den printer.
            userList.toString()
        }
    }


    //og en til at få en bestemt user udfra id??
}