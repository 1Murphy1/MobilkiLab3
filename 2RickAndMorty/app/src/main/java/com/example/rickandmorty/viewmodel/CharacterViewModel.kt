package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = ApiClient.api.getAllCharacters()
                _characters.value = response.results
            } catch (e: IOException) {
                _errorMessage.value = "Проверьте подключение к интернету."
            } catch (e: HttpException) {
                _errorMessage.value = "Ошибка сервера: ${e.message}"
            } catch (e: Exception) {
                _errorMessage.value = "Неизвестная ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
