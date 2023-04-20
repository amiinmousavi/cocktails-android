package com.example.cocktails.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cocktails.model.database.CocktailRepository

class CocktailViewModel(private val repository: CocktailRepository) : ViewModel() {
}