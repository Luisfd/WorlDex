package com.example.worldex.data.network.response

import android.util.Log
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexModel
import com.example.worldex.domain.model.DexResultsModel
import com.example.worldex.domain.model.PokeEntryModel
import com.example.worldex.domain.model.PokeModel
import com.google.gson.annotations.SerializedName

data class DexListResponse(
    @SerializedName("results") val results: List<DexResultsModel>
) {


    fun toDomain(): MutableList<DexInfo> {

        var dexList = mutableListOf<DexInfo>()

        for (posicion in results.indices){
            var dexResultsModel : DexResultsModel = results.elementAt(posicion)
            val id = if (posicion < 9) posicion + 1 else posicion + 2
            val name = dexResultsModel.name
            val url = dexResultsModel.url
            val image:String = when(id%10){
                1 -> "https://static.wikia.nocookie.net/pokemon/images/8/8d/Pokedex_LGPE.png/revision/latest?cb=20190830002740"
                2 -> "https://static.wikia.nocookie.net/pokemon/images/5/5c/Gen_I_Pokedex.png/revision/latest?cb=20100717083120"
                3 -> "https://static.wikia.nocookie.net/pokemon/images/4/4f/Pok%C3%A9dex_GSC.png/revision/latest?cb=20100717083141"
                4 -> "https://static.wikia.nocookie.net/pokemon/images/9/90/Pokedex_Hoenn_Region.png/revision/latest?cb=20221030051942"
                5 -> "https://static.wikia.nocookie.net/pokemon/images/1/1f/Pokedex_FRLG.png/revision/latest?cb=20221030052741"
                6 -> "https://static.wikia.nocookie.net/pokemon/images/7/74/Pok%C3%A9dex_DP.png/revision/latest?cb=20211216054812"
                7 -> "https://static.wikia.nocookie.net/pokemon/images/6/6f/Pok%C3%A9dex_Pt.png/revision/latest?cb=20110528144645"
                8 -> "https://static.wikia.nocookie.net/pokemon/images/9/91/Pokedex_ORAS.png/revision/latest?cb=20141012105918"
                9 -> "https://static.wikia.nocookie.net/pokemon/images/1/19/Pokedex_of_Alola.jpg/revision/latest?cb=20161101103424"
                0 -> "https://static.wikia.nocookie.net/pokemon/images/8/8d/Pokedex_LGPE.png/revision/latest?cb=20190830002740"
                else -> "https://static.wikia.nocookie.net/pokemon/images/5/5c/Gen_I_Pokedex.png/revision/latest?cb=20100717083120"
            }
            var dexInfo = DexInfo(
                id.toString(),
                name,
                image
            )
            dexList.add(dexInfo)
        }
        return dexList


        /*
        var entry = listOf<PokeEntryModel>()
       // var entry = pokemon_entries as List<PokeEntryModel>
       // var a = entry
        var ods = pokemon_entries.elementAt(0)
        var pokeList = mutableListOf<PokeModel>()
        //var asd = a.elementAt(0)
        Log.i("asd","asd al asd${ods}")*/

    }
}