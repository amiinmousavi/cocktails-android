package com.example.cocktails.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cocktails.model.entities.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDAO {

    @Insert
    suspend fun insertCocktail(cocktail: Cocktail)

    @Query("SELECT * FROM COCKTAILS_TABLE ORDER BY ID_DRINK")
    fun getAllCocktails(): Flow<List<Cocktail>>
}