package com.example.minstrom
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentId
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf


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

class UserViewModel: ViewModel() {
    val repository = FirestoreRepository()
    //instands af firestoreRepos..

    private val _userList = mutableStateListOf<User>()
    val userList: List<User> get() = _userList //ide fra chat til state opstilling


    fun getFromDatabase() {
        viewModelScope.launch {
            var result = repository.get()
            //println(result) //bliver nødt til at vente på at den har hentet alle før den printer.
            _userList.clear()
            _userList.addAll(result)
        }
    }

    //og en til at få en bestemt user udfra id
    fun getOneUserFromDatabase(userId: String) {
        viewModelScope.launch {
            var result = repository.getOne(userId)
            println(result) //bliver nødt til at vente på at den har hentet alle før den printer.
            userList.toString()
        }
    }

    //eller man kunne undersøge mange til mange forhold i firestore
    //https://fireship.io/courses/firestore-data-modeling/relational-many-to-many/

}
