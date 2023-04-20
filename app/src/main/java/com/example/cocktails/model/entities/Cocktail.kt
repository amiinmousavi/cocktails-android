package com.example.cocktails.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_dishes_table")
data class Cocktail(
    @ColumnInfo(name = "drink") val strDrink: String,
    @ColumnInfo(name = "category") val strCategory: String,
    @ColumnInfo(name = "alcoholic") val strAlcoholic: String,
    @ColumnInfo(name = "glass") val strGlass: String,
    @ColumnInfo(name = "instructions") val strInstructions: String,
    @ColumnInfo(name = "ingredient") val strIngredient: String,
    @ColumnInfo(name = "measure") val strMeasure: String,
    @ColumnInfo(name = "date_modified") val dateModified: String,

    @ColumnInfo(name = "favorite_cocktail") var favoriteCocktail: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)