package com.github.lincolnstuart.desafiointegradorfirebase.model.game

data class Game(
    val name: String,
    val createdAt: String,
    val description: String,
) {
    var ownerUid: String? = null

    fun toHashMap(): HashMap<String, Any?> {
        return hashMapOf(
            "name" to name,
            "created_at" to createdAt,
            "description" to description,
            "owner" to ownerUid
        )
    }
}