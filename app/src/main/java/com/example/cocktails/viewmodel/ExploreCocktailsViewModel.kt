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

    init {
        _navigateToNonAlcoholicDrinks.value = false
    }

    // navigation
    fun btnNavigateToNonAlcoholicDrinksClicked() {
        Log.i("clickNonAlcoholic", "exporecocktailsviewmodel")
        _navigateToNonAlcoholicDrinks.value = true
    }

    fun btnNavigateToNonAlcoholicDrinksFinished() {
        _navigateToNonAlcoholicDrinks.value = false
    }

}