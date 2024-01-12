package com.example.sdutest.core.model.pokemon

import com.example.sdutest.core.model.pokemon.Ability
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Abilities (

    @SerialName("ability"   ) var ability  : Ability? = Ability(),
    @SerialName("is_hidden" ) var isHidden : Boolean? = null,
    @SerialName("slot"      ) var slot     : Int?     = null

)