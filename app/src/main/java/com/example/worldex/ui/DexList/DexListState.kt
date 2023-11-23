package com.example.worldex.ui.DexList

import com.example.worldex.domain.model.PokeEntryModel
import com.example.worldex.domain.model.PokeModel

sealed class DexListState {
    data object Loading:DexListState()
    data class Error(val error:String):DexListState()
    data class Success(val list: List<PokeModel>):DexListState()
}