package com.example.cocktails.utils

object Constants {

    const val CATEGORY: String = "Category"
    const val GLASS: String = "Glass"
    const val ALCOHOLIC: String = "Alcoholic"

    // TODO: deze categoriën, glazen, alcoholic, ... ophalen van API
    fun categories():ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Ordinary Drink")
        list.add("Cocktail")
        list.add("Shake")
        list.add("Other/Unknown")
        list.add("Cocoa")
        list.add("Shot")
        list.add("Coffee/Tea")
        list.add("Homemade Liqueur")
        list.add("Punch/Party Drink")
        list.add("Beer")
        list.add("Soft Drink")
        return list
    }

    fun glasses():ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Highball glass")
        list.add("Cocktail glass")
        list.add("Old-fashioned glass")
        list.add("Whiskey Glass")
        list.add("Collins glass")
        list.add("Pousse cafe glass")
        list.add("Champagne flute")
        list.add("Whiskey sour glass")
        list.add("Cordial glass")
        list.add("Brandy snifter")
        list.add("White wine glass")
        list.add("Nick and Nora Glass")
        list.add("Hurricane glass")
        list.add("Coffee mug")
        list.add("Shot glass")
        list.add("Jar")
        list.add("Irish coffee cup")
        list.add("Punch bowl")
        list.add("Pitcher")
        list.add("Pint glass")
        list.add("Copper Mug")
        list.add("Wine Glass")
        list.add("Beer mug")
        list.add("Margarita/Coupette glass")
        list.add("Beer pilsner")
        list.add("Beer Glass")
        list.add("Parfait glass")
        list.add("Mason jar")
        list.add("Margarita glass")
        list.add("Coupe Glass")
        list.add("Balloon Glass")
        list.add("Martini Glass")
        return list.sorted() as ArrayList<String>
    }

    fun alcoholic():ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Alcoholic")
        list.add("Non-Alcoholic")
        list.add("Optional Alcoholic")
        return list
    }

}
