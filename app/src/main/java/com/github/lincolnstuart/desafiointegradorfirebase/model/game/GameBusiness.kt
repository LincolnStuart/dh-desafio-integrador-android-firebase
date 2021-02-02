package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.AuthenticationRepository
import com.google.firebase.firestore.DocumentReference

class GameBusiness {

    private val repo: GameRepository by lazy{
        GameRepository()
    }
    private val authRepo: AuthenticationRepository by lazy{
        AuthenticationRepository()
    }

    fun add(
        game: Game,
        onSuccess: (DocumentReference) -> Unit,
        onFailure: (String) -> Unit
    ) {
        game.ownerUid = authRepo.getUser().toString()
        repo.add(game, onSuccess, onFailure)
    }

}