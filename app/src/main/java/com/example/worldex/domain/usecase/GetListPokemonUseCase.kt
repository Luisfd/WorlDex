package com.example.worldex.domain.usecase

import com.example.worldex.domain.Repository
import javax.inject.Inject

class GetListPokemonUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(type:String) = repository.getListPokemon(type)
}