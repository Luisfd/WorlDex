package com.example.worldex.domain

import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.PokeModel

interface Repository {
    suspend fun getListPokemon(type:String): List<PokeModel>?
    suspend fun getListPokedex(): List<DexInfo>?
}