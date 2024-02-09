package com.example.worldex.domain.usecase

import com.example.worldex.domain.Repository
import javax.inject.Inject

class GetListPokedexUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getListPokedex()
}