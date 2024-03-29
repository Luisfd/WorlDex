package com.example.worldex.data

import android.util.Log
import com.example.worldex.data.network.DexApiService
import com.example.worldex.domain.Repository
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.PokeModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: DexApiService): Repository {

    override suspend fun getListPokemon(type: String): List<PokeModel>? {
        runCatching {  apiService.getDex(type)}
            .onSuccess { return it.toDomain()}
            .onFailure { Log.i("problemas","Ha ocurrido un error ${it.message}") }
        return null
    }

    override suspend fun getListPokedex(): List<DexInfo>? {
        runCatching {  apiService.getListDex()}
            .onSuccess { return it.toDomain()}
            .onFailure { Log.i("problemas","Ha ocurrido un error ${it.message}") }
        return null
    }

}