package com.example.worldex.ui.dex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldex.data.providers.DexProvider
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexModel
import com.example.worldex.domain.usecase.GetListPokedexUseCase
import com.example.worldex.domain.usecase.GetListPokemonUseCase
import com.example.worldex.ui.DexList.DexListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DexViewModel @Inject constructor(private val getListPokedexUseCase: GetListPokedexUseCase):ViewModel(){
//class DexViewModel @Inject constructor(dexProvider: DexProvider) : ViewModel(){

    // private var _dex = MutableStateFlow<DexState>(DexState.Loading)  //Vamos a dejar el hacerlo así para cuando ya hayamos hecho en local mostrar una lista de pokemon
/*
    private var _dex = MutableStateFlow<List<DexInfo>>(emptyList())
    val dex: StateFlow<List<DexInfo>> = _dex

    init {
        _dex.value = dexProvider.getDex()
    }

 */

    private var _state = MutableStateFlow<DexState>(DexState.Loading)
    val state: StateFlow<DexState> = _state

    fun getListPokedex() {
        viewModelScope.launch {
            _state.value = DexState.Loading
            val result = withContext(Dispatchers.IO){getListPokedexUseCase()}
            if(result!=null){
                //convertResult(result)
                _state.value = DexState.Success(result)
            }else{
                _state.value = DexState.Error("Parece que el profesor no tiene dex que mostrar, inténtalo de nuevo")
            }
        }
    }

}