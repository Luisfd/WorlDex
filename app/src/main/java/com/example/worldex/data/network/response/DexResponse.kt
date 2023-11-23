package com.example.worldex.data.network.response

import android.util.Log
import com.example.worldex.domain.model.DexModel
import com.example.worldex.domain.model.PokeEntryModel
import com.example.worldex.domain.model.PokeModel
import com.google.gson.annotations.SerializedName

data class DexResponse(
    @SerializedName("id") val id: String,
    @SerializedName("is_main_series") val is_main_series: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("pokemon_entries") val pokemon_entries: List<PokeEntryModel>
) {


    fun toDomain(): MutableList<PokeModel> {

        var pokeList = mutableListOf<PokeModel>()

        for (posicion in pokemon_entries.indices){
            var pokeEntry : PokeEntryModel = pokemon_entries.elementAt(posicion)
            val id = pokeEntry.entry_number
            var pokeModel = PokeModel(
                id,
                pokeEntry.pokemon_species.name,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            )
            pokeList.add(pokeModel)
        }
        return pokeList


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