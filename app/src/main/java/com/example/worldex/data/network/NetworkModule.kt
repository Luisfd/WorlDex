package com.example.worldex.data.network

import android.util.Log
import com.example.worldex.data.RepositoryImpl
import com.example.worldex.data.core.interceptors.AuthInterceptor
import com.example.worldex.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/")//No me va el Base_URL del gradle
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }


    @Provides
    fun provideDexApiServices(retrofit: Retrofit):DexApiService{
        return retrofit.create(DexApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: DexApiService): Repository {
        return RepositoryImpl(apiService)
    }

}