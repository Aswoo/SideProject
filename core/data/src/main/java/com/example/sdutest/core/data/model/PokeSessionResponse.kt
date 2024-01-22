package com.example.sdutest.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokeSessionResponse(
    val id: String,
    val name : String,
    val types : List<String>,
    val height : Float,
    val weight : Float,
    val sex : List<String>?,
    val category : String,
    val content: String,
    val image: String,
)