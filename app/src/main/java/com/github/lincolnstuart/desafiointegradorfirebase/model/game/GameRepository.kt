package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import android.net.Uri
import android.util.Log
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.DEFAULT_FAILURE_MESSAGE
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_COLLECTION_PATH
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class GameRepository {

    private val firestore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }

    fun add(
        game: Game,
        thumbnail: Uri,
        onSuccess: (DocumentReference) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestore.collection(GAME_COLLECTION_PATH).add(game.toHashMap())
            .addOnSuccessListener {
                sendImage(thumbnail, it, onSuccess)
            }.addOnFailureListener {
                onFailure(it.localizedMessage?.toString() ?: DEFAULT_FAILURE_MESSAGE)
            }
    }

    fun getGames(
        ownerUid: String,
        onSuccess: (MutableList<DocumentSnapshot>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestore.collection(GAME_COLLECTION_PATH)
            .whereEqualTo(Constants.GAME_FIELD_OWNER, ownerUid)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    onFailure(e.localizedMessage?.toString() ?: DEFAULT_FAILURE_MESSAGE)
                } else {
                    onSuccess(value?.documents ?: mutableListOf())
                }
            }
    }

    private fun sendImage(
        thumbnail: Uri,
        documentReference: DocumentReference,
        onSuccess: (DocumentReference) -> Unit
    ) {
        val reference = storage.reference
        val images = reference.child("${documentReference.id}/thumbnail.jpg")
        images.putFile(thumbnail).addOnSuccessListener {
            onSuccess(documentReference)
        }
    }

    fun getStorageReference(game: Game) = storage.reference.child("${game.uid}/thumbnail.jpg")

}