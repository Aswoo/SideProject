package com.example.sdutest.core.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
data class PokeSession(
    val id: String,
    val name : String,
    @JsonNames("en_name")
    val enName : String,
    val types : List<String>,
    val height : Float,
    val weight : Float,
    val sex : List<String>?,
    val category : String,
    val content: String,
    val image : String,
    val isBookmarked : Boolean = false,
)