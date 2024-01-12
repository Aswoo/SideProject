package com.example.sdutest.core.data.repository

import com.example.sdutest.core.model.pokemon.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemon(name : String) : PokemonResponse
}