package com.example.sdutest.core.model.pokemon

import com.example.sdutest.core.model.pokemon.MoveLearnMethod
import com.example.sdutest.core.model.pokemon.VersionGroup

import kotlinx.serialization.SerialName


data class VersionGroupDetails (

    @SerialName("level_learned_at"  ) var levelLearnedAt  : Int?             = null,
    @SerialName("move_learn_method" ) var moveLearnMethod : MoveLearnMethod? = MoveLearnMethod(),
    @SerialName("version_group"     ) var versionGroup    : VersionGroup?    = VersionGroup()

)