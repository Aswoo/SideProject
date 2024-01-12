package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Version (

  @SerialName("name" ) var name : String? = null,
  @SerialName("url"  ) var url  : String? = null

)