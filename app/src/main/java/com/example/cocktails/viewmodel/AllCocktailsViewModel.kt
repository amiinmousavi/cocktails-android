package com.example.cocktails.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktails.model.database.CocktailRepository
import com.example.cocktails.model.entities.Cocktail
import kotlinx.coroutines.launch

// viewmodels: responsible for holding and processing all the data needed for the ui
class AllCocktailsViewModel (private val repository: CocktailRepository) : ViewModel() {

    fun insert(cocktail: Cocktail) = viewModelScope.launch {
        repository.insertCocktail(cocktail)
    }
    private var _allCocktails: LiveData<List<Cocktail>> = repository.allCocktails.asLiveData()
    val allCocktails: LiveData<List<Cocktail>>
        get() = _allCocktails
}


