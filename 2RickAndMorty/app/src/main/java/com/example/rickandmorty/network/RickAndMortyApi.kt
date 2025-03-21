package com.example.rickandmorty.network

import com.example.rickandmorty.model.CharacterResponse
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(): CharacterResponse
}
