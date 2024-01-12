package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName


data class Icons (

  @SerialName("front_default" ) var frontDefault : String? = null,
  @SerialName("front_female"  ) var frontFemale  : String? = null

)