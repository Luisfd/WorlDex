package com.example.worldex.domain.model

import com.google.gson.annotations.SerializedName

data class DexResultsModel (
    @SerializedName("name")val name: String,
    @SerializedName("url")val url: String
    )


