package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
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

    fun addGame(game: Game, thumbnail: Uri) {
        business.add(
            game,
            thumbnail,
            { documentReference: DocumentReference ->
                documentReferenceLiveData
                    .postValue(documentReference)
            },
            { message: String -> errorMessageLiveData.postValue(message) })
    }


    fun validateName(value: String) =
        business.validateName(value)

    fun validateReleaseDate(value: String) =
        business.validateReleaseDate(value)

    fun validateDescription(value: String) =
        business.validateDescription(value)


}