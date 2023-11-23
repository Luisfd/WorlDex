package com.example.worldex.ui.dex

sealed class DexState {
    data object Loading:DexState()
    data class Error(val error:String):DexState()
    data class Success(val listPokemon:List<String>):DexState() //Lista de un objeto pokemon con numero nombre e imagen o algo así?
}