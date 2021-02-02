package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.DEFAULT_FAILURE_MESSAGE
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_COLLECTION_PATH
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
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
        firestore.collection(GAME_COLLECTION_PATH).add(game.toHashMap())
            .addOnSuccessListener {
                onSuccess(it)
            }.addOnFailureListener {
                onFailure(it.localizedMessage?.toString() ?: DEFAULT_FAILURE_MESSAGE)
            }
    }

    fun getGames(ownerUid: String,
                 onSuccess: (MutableList<DocumentSnapshot>) -> Unit,
                 onFailure: (String) -> Unit){
        firestore.collection(GAME_COLLECTION_PATH)
            .whereEqualTo(Constants.GAME_FIELD_OWNER, ownerUid)
            .addSnapshotListener{ value, e ->
                if(e != null){
                    onFailure(e.localizedMessage?.toString() ?: DEFAULT_FAILURE_MESSAGE)
                } else {
                    onSuccess(value?.documents?: mutableListOf())
                }
            }
    }

}