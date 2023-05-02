package com.example.cocktails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExploreCocktailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ExploreCocktails Fragment"
    }
    val text: LiveData<String> = _text
}