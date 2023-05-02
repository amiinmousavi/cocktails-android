package com.example.cocktails.model.entities

object RandomCocktail {
    data class Drinks(
        val drinks: List<Drink>
    )

    data class Drink(
        val dateModified: String,
        val idDrink: String,
        val strAlcoholic: String,
        val strCategory: String,
        val strDrink: String,
        val strDrinkThumb: String,
        val strGlass: String,
        val strImageSource: Any,
        val strIngredient1: String,
        val strIngredient2: String,
        val strIngredient3: String,
        val strIngredient4: String,
        val strIngredient5: String,
        val strIngredient6: String,
        val strIngredient7: String,
        val strIngredient8: String,
        val strIngredient9: Any,
        val strIngredient10: Any,
        val strIngredient11: Any,
        val strIngredient12: Any,
        val strIngredient13: Any,
        val strIngredient14: Any,
        val strIngredient15: Any,
        val strInstructions: String,
        val strMeasure1: String,
        val strMeasure2: String,
        val strMeasure3: String,
        val strMeasure4: String,
        val strMeasure5: Any,
        val strMeasure6: Any,
        val strMeasure7: String,
        val strMeasure8: Any,
        val strMeasure9: Any,
        val strMeasure10: Any,
        val strMeasure11: Any,
        val strMeasure12: Any,
        val strMeasure13: Any,
        val strMeasure14: Any,
        val strMeasure15: Any,
    )
}