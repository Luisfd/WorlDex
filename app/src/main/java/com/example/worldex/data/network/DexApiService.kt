package com.example.worldex.data.network

import com.example.worldex.data.network.response.DexListResponse
import com.example.worldex.data.network.response.DexResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DexApiService {

    //Lo que aparece entre {} en el get tiene que llamarse igual en el @Path
  /*  @GET("/{type}")
    suspend fun getDex(@Path("type") type:String): DexResponse*/
    @GET("/api/v2/pokedex/{type}")
    suspend fun getDex(@Path("type") type:String): DexResponse

    @GET("/api/v2/pokedex/?offset=0&limit=120")
    suspend fun getListDex(): DexListResponse
   /* @GET
    suspend fun getDex(@Url url:String): DexResponse*/
}