package com.example.cocktails.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.view.fragments.ExploreCocktailsFragmentDirections

class ExploreCocktailsViewModel : ViewModel() {

    private val _navigateToNonAlcoholicDrinks = MutableLiveData<Boolean>()
    val navigateToNonAlcoholicDrinks : LiveData<Boolean>
        get() {return _navigateToNonAlcoholicDrinks}

    private val _navigateToRandomCocktail = MutableLiveData<Boolean>()
    val navigateToRandomCocktail: LiveData<Boolean>
        get() {return _navigateToRandomCocktail}

    init {
        _navigateToNonAlcoholicDrinks.value = false
        _navigateToRandomCocktail.value = false
    }

    // navigation {TO NON ALCOHOLIC DRINKS}
    fun btnNavigateToNonAlcoholicDrinksClicked() {
        _navigateToNonAlcoholicDrinks.value = true
    }

    fun btnNavigateToNonAlcoholicDrinksFinished() {
        _navigateToNonAlcoholicDrinks.value = false
    }

    // navigation {TO RANDOM COCKTAIL}
    fun btnNavigateToRandomCocktailClicked() {
        _navigateToRandomCocktail.value = true
    }

    fun btnNavigateToRandomCocktailFinished() {
        _navigateToRandomCocktail.value = false
    }

}