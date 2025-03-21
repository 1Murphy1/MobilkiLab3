package com.example.rickandmorty.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val api: RickAndMortyApi by lazy {
        createRetrofit().create(RickAndMortyApi::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
