package com.example.sdutest.core.data.repository

import com.example.sdutest.core.data.api.GithubApi
import com.example.sdutest.core.model.pokemon.PokemonResponse
import javax.inject.Inject

internal class DefaultPokeRepository @Inject constructor(
    private val githubApi: GithubApi,
) : PokemonRepository {

    override suspend fun getPokemon(name: String) : PokemonResponse{
        return githubApi.getPokemonByName(name)
    }
}
