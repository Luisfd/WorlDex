package com.example.worldex.ui.dex

import com.example.worldex.domain.model.DexInfo

sealed class DexState {
    data object Loading:DexState()
    data class Error(val error:String):DexState()
    data class Success(val listPokedex:List<DexInfo>):DexState() //Lista de un objeto pokemon con numero nombre e imagen o algo así?
}