package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.GameBusiness
import com.google.firebase.firestore.DocumentSnapshot

class GameListViewModel : DefaultBaseViewModel() {

    private val business: GameBusiness by lazy {
        GameBusiness()
    }
    val documentsSnapshotListLiveData: MutableLiveData<MutableList<DocumentSnapshot>> by lazy {
        MutableLiveData()
    }
    val errorMessageLiveData: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun getGames() {
        business.getGames(
            { documentsSnapshotList: MutableList<DocumentSnapshot> ->
                documentsSnapshotListLiveData.postValue(documentsSnapshotList)
            },
            { message: String -> errorMessageLiveData.postValue(message) }
        )
    }

    fun getThumbnailReference(game: Game) = business.getThumbnailReference(game)


}