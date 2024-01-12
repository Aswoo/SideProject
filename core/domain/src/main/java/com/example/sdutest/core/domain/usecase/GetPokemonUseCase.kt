package com.example.sdutest.core.domain.usecase

import com.example.sdutest.core.data.repository.PokemonRepository
import com.example.sdutest.core.data.repository.SessionRepository
import com.example.sdutest.core.model.Session
import com.example.sdutest.core.model.pokemon.PokemonResponse
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {

    suspend operator fun invoke(name: String): PokemonResponse {
        return pokemonRepository.getPokemon(name)
    }
}