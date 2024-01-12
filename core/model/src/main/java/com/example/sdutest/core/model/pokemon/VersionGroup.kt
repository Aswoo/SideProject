package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName


data class VersionGroup (

  @SerialName("name" ) var name : String? = null,
  @SerialName("url"  ) var url  : String? = null

)