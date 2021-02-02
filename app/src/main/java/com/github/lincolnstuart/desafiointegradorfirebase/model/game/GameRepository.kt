package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GameRepository {

    private val firestore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun add(
        game: Game,
        onSuccess: (DocumentReference) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestore.collection("games").add(game.toHashMap())
            .addOnSuccessListener {
                onSuccess(it)
            }.addOnFailureListener {
                onFailure(it.localizedMessage?.toString() ?: ":/")
            }
    }

}