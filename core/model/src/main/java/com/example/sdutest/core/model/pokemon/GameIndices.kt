package com.example.sdutest.core.model.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndices (

  @SerialName("game_index" ) var gameIndex : Int?     = null,
  @SerialName("version"    ) var version   : Version? = Version()

)