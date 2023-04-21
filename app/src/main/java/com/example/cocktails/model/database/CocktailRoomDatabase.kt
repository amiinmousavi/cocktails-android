package com.example.cocktails.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cocktails.model.entities.Cocktail

@Database(entities = [Cocktail::class], version = 1)
abstract class CocktailRoomDatabase : RoomDatabase() {

    abstract fun cocktailDAO(): CocktailDAO
    companion object {
        @Volatile
        private var INSTANCE: CocktailRoomDatabase? = null

        fun getDatabase(context: Context): CocktailRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocktailRoomDatabase::class.java,
                    "cocktail_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}