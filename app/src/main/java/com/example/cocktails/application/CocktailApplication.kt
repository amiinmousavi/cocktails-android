package com.example.cocktails.application

import android.app.Application
import com.example.cocktails.model.database.CocktailRepository
import com.example.cocktails.model.database.CocktailRoomDatabase

class CocktailApplication : Application() {
    private val database by lazy {CocktailRoomDatabase.getDatabase((this@CocktailApplication))}

    val repository by lazy { CocktailRepository(database.cocktailDAO()) }
}