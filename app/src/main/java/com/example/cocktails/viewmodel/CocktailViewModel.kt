package com.example.cocktails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktails.model.database.CocktailRepository
import com.example.cocktails.model.entities.Cocktail
import kotlinx.coroutines.launch

class CocktailViewModel(private val repository: CocktailRepository) : ViewModel() {

    fun insert(cocktail: Cocktail) = viewModelScope.launch {
        repository.insertCocktailData(cocktail)
    }

    class CocktailViewModelFactory(private val repository: CocktailRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            if(modelClass.isAssignableFrom(CocktailViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return CocktailViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown Viewmodel Class")
        }
    }
}