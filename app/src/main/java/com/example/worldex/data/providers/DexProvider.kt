package com.example.worldex.data.providers

import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexInfo.*
import javax.inject.Inject

class DexProvider @Inject constructor() {
    fun getDex(): List<DexInfo>{
        return listOf(
          /*  Bulbasaur,
            Ivysaur,
            Venusaur,
            Charmander*/
        )
    }
}