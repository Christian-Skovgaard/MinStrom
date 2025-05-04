package com.example.minstrom.data.user

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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