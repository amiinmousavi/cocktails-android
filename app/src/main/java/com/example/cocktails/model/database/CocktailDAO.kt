package com.example.cocktails.model.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.cocktails.model.entities.Cocktail

@Dao
interface CocktailDAO {

    @Insert
    fun insertCocktailDetails(cocktail: Cocktail)
}