package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats (

  @SerialName("base_stat" ) var baseStat : Int?  = null,
  @SerialName("effort"    ) var effort   : Int?  = null,
  @SerialName("stat"      ) var stat     : Stat? = Stat()

)