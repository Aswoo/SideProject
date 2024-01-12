package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Types (

  @SerialName("slot" ) var slot : Int?  = null,
  @SerialName("type" ) var type : Type? = Type()

)