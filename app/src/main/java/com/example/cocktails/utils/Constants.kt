package com.example.cocktails.utils

object Constants {
    // String
    const val CATEGORY: String = "Category"
    const val GLASS: String = "Glass"
    const val ALCOHOLIC: String = "Alcoholic"

    // Image Source
    const val COCKTAIL_IMAGE_SOURCE_LOCAL : String = "Local"
    const val COCKTAIL_IMAGE_SOURCE_ONLINE : String = "Online"

    // Cocktails Api
    const val API_BASE_URL: String = "https://www.thecocktaildb.com/api/json/v1/1/"
    const val API_ENDPOINT_RANDOM_COCKTAIL: String = "random.php"
    const val API_ENDPOINT_NON_ALCOHOLIC: String = "filter.php?a=Non_Alcoholic"

    // Filter Options
    const val API_ENDPOINT_CATEGORIES: String = "list.php?c=list"
    const val API_ENDPOINT_GLASSES: String = "list.php?g=list"
    const val API_ENDPOINT_INGREDIENTS: String = "list.php?i=list"
    const val API_ENDPOINT_ALCOHOLIC: String = "list.php?a=list"

    // TODO: deze categoriën, glazen, alcoholic, ... ophalen van API
    fun categories(): ArrayList<String> {
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
        
        return sortAlphabetically(
            list
        )
    }

    fun glasses(): ArrayList<String> {
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

        return sortAlphabetically(
            list
        )

    }

    fun alcoholic(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Alcoholic")
        list.add("Non-Alcoholic")
        list.add("Optional Alcoholic")
        return list
    }

    fun sortAlphabetically(arrayList: ArrayList<String>): ArrayList<String> {
        var list = arrayList as MutableList<String>
        list.sortWith(Comparator { o1: String, o2: String ->
            o1.compareTo(o2)
        })
        return list as ArrayList<String>
    }
}
