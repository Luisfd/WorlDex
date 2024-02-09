package com.example.worldex.domain.model

import com.example.worldex.R

/*
sealed class DexInfo (val img: Int, val name:Int){
    data object Bulbasaur:DexInfo(R.drawable.bulbasaur,R.string.bulbasaur)
    data object Ivysaur:DexInfo(R.drawable.ivysaur,R.string.ivysaur)
    data object Venusaur:DexInfo(R.drawable.venusaur,R.string.venusaur)
    data object Charmander:DexInfo(R.drawable.charmander,R.string.charmander)
}*/

data class DexInfo (
    val id: String,
    val name: String,
    val img: String
)