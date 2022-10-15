package com.example.app_movil_alerta_terremotos.Auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase

class StoreRepository(){
    val user = Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null

    fun getUserId():String = Firebase.auth.currentUser?.uid.orEmpty()
  //  private  val perfil: CollectionReference = Firebase
   //    .firestore.collection(NOTES_COLLECTION_REF)
}
sealed class Resources<T>(
    val date: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>:Resources<T>()
    class  Success<T>(data: T?):Resources<T>(date = data)
    class  Error<T>(throwable: Throwable?):Resources<T>(throwable = throwable)
}