package com.example.sdutest.feature.main

import com.example.sdutest.core.model.pokemon.PokemonResponse


sealed interface SessionDetailUiState {

    object Loading : SessionDetailUiState

    data class Success(val pokemonRes: PokemonResponse, val bookmarked: Boolean = false) : SessionDetailUiState
}
