package com.example.cocktails.model.entities

object NonAlcoholicDrink {
data class Drinks(
    val drinks: List<Drink>
)

data class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)
}