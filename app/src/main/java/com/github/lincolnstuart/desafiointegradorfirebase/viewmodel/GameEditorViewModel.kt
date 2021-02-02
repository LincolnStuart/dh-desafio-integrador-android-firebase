package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.GameBusiness
import com.google.firebase.firestore.DocumentReference

class GameEditorViewModel : DefaultBaseViewModel() {

    private val business: GameBusiness by lazy {
        GameBusiness()
    }
    val documentReferenceLiveData: MutableLiveData<DocumentReference> by lazy {
        MutableLiveData()
    }
    val errorMessageLiveData: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun addGame(game: Game) {
        business.add(
            game, { documentReference: DocumentReference ->
                documentReferenceLiveData
                    .postValue(documentReference)
            },
            { message: String -> errorMessageLiveData.postValue(message) })
    }
}