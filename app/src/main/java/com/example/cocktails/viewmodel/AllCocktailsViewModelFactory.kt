package com.example.cocktails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cocktails.model.database.CocktailRepository

class AllCocktailsViewModelFactory(private val repository: CocktailRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AllCocktailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllCocktailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}