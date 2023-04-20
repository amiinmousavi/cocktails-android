package com.example.cocktails.model.database

import androidx.annotation.WorkerThread
import com.example.cocktails.model.entities.Cocktail

class CocktailRepository(private val cocktailDAO: CocktailDAO) {

    @WorkerThread
    suspend fun insertFavoriteCocktail(cocktail: Cocktail){
        cocktailDAO.insertFavoriteCocktailDetails(cocktail)
    }
}