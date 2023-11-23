package com.example.worldex.ui.dex

import androidx.lifecycle.ViewModel
import com.example.worldex.data.providers.DexProvider
import com.example.worldex.domain.model.DexInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DexViewModel @Inject constructor(dexProvider: DexProvider) : ViewModel(){

    // private var _dex = MutableStateFlow<DexState>(DexState.Loading)  //Vamos a dejar el hacerlo así para cuando ya hayamos hecho en local mostrar una lista de pokemon

    private var _dex = MutableStateFlow<List<DexInfo>>(emptyList())
    val dex: StateFlow<List<DexInfo>> = _dex

    init {
        _dex.value = dexProvider.getDex()
    }

}