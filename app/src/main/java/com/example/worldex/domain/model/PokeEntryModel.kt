package com.example.worldex.domain.model

import com.google.gson.annotations.SerializedName

data class PokeEntryModel (
    @SerializedName("entry_number")val entry_number: String,
    @SerializedName("pokemon_species")val pokemon_species: PokeSpeciesModel
    )

data class PokeSpeciesModel (
    @SerializedName("name")val name: String,
    @SerializedName("url")val url: String
)

