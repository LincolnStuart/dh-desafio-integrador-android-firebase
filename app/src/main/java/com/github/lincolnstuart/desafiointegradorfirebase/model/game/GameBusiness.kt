package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import android.net.Uri
import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.AuthenticationRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

class GameBusiness {

    private val repo: GameRepository by lazy{
        GameRepository()
    }
    private val authRepo: AuthenticationRepository by lazy{
        AuthenticationRepository()
    }

    fun add(
        game: Game,
        thumbnail: Uri,
        onSuccess: (DocumentReference) -> Unit,
        onFailure: (String) -> Unit
    ) {
        game.ownerUid = authRepo.getUser().toString()
        repo.add(game, thumbnail, onSuccess, onFailure)
    }

    fun getGames(onSuccess: (MutableList<DocumentSnapshot>) -> Unit,
                 onFailure: (String) -> Unit){
        repo.getGames(authRepo.getUser().toString(), onSuccess, onFailure)
    }

    fun getThumbnailReference(game: Game) = repo.getStorageReference(game)

}