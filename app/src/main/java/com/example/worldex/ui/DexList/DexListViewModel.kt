package com.example.worldex.ui.DexList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexModel
import com.example.worldex.domain.model.PokeEntryModel
import com.example.worldex.domain.model.PokeModel
import com.example.worldex.domain.usecase.GetListPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DexListViewModel @Inject constructor(private val getListPokemonUseCase: GetListPokemonUseCase):ViewModel(){

    private var _state = MutableStateFlow<DexListState>(DexListState.Loading)
    val state: StateFlow<DexListState> = _state

    //lateinit var dex:DexModel

    fun getListPokemon(type: String) {
        //dex = type
        viewModelScope.launch {
            _state.value = DexListState.Loading
            val result = withContext(Dispatchers.IO){getListPokemonUseCase(type)}
            if(result!=null){
                //convertResult(result)
                _state.value = DexListState.Success(result)
            }else{
                _state.value = DexListState.Error("Pide una nueva dex al profesor, o intentalo más tarde")
            }
        }
    }

}