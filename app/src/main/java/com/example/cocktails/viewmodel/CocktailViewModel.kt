package com.example.cocktails.viewmodel

import androidx.lifecycle.*
import com.example.cocktails.model.database.CocktailRepository
import com.example.cocktails.model.entities.Cocktail
import kotlinx.coroutines.launch

class CocktailViewModel (private val repository: CocktailRepository) : ViewModel() {
    fun insert(cocktail: Cocktail) = viewModelScope.launch {
        repository.insertCocktail(cocktail)
    }

    val allCocktails: LiveData<List<Cocktail>> = repository.allCocktails.asLiveData()

}


