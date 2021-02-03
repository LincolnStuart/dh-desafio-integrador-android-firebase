package com.github.lincolnstuart.desafiointegradorfirebase.model.game

import android.os.Parcelable
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_CREATED_AT
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_DESCRIPTION
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_NAME
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants.GAME_FIELD_OWNER
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val createdAt: String,
    val description: String,
    var ownerUid: String? = null,
    var uid: String? = null
) : Parcelable {

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            GAME_FIELD_NAME to name,
            GAME_FIELD_CREATED_AT to createdAt,
            GAME_FIELD_DESCRIPTION to description,
            GAME_FIELD_OWNER to ownerUid
        )
    }

}