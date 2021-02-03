package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import com.github.lincolnstuart.desafiointegradorfirebase.model.game.Game
import com.github.lincolnstuart.desafiointegradorfirebase.model.game.GameBusiness

class GameDetailViewModel: DefaultBaseViewModel() {

    private val business: GameBusiness by lazy {
        GameBusiness()
    }

    fun getThumbnailReference(game: Game) = business.getThumbnailReference(game)

}