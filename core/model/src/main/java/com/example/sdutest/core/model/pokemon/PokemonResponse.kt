package com.example.sdutest.core.model.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(

    @SerialName("abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @SerialName("base_experience") var baseExperience: Int? = null,
    @SerialName("forms") var forms: ArrayList<Forms> = arrayListOf(),
    @SerialName("game_indices") var gameIndices: ArrayList<GameIndices> = arrayListOf(),
    @SerialName("height") var height: Int? = null,
//    @SerialName("held_items") var heldItems: ArrayList<String> = arrayListOf(),
    @SerialName("id") var id: Int? = null,
    @SerialName("is_default") var isDefault: Boolean? = null,
    @SerialName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("past_abilities") var pastAbilities: ArrayList<String> = arrayListOf(),
    @SerialName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @SerialName("species") var species: Species? = Species(),
    @SerialName("sprites") var sprites: Sprites? = Sprites(),
    @SerialName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerialName("types") var types: ArrayList<Types> = arrayListOf(),
    @SerialName("weight") var weight: Int? = null

)