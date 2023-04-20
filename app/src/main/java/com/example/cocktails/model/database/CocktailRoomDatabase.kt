package com.example.cocktails.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktails.model.entities.Cocktail

@Database(entities = [Cocktail::class], version = 1)
abstract class CocktailRoomDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: CocktailRoomDatabase? = null
    }
}