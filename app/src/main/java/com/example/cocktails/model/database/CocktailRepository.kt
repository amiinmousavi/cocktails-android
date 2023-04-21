package com.example.cocktails.model.database

import androidx.annotation.WorkerThread
import com.example.cocktails.model.entities.Cocktail
import kotlinx.coroutines.flow.Flow

class CocktailRepository(private val cocktailDAO: CocktailDAO) {

    @WorkerThread
    suspend fun insertCocktail(cocktail: Cocktail){
        cocktailDAO.insertCocktail(cocktail)
    }

    val allCocktails: Flow<List<Cocktail>> = cocktailDAO.getAllCocktails()
}