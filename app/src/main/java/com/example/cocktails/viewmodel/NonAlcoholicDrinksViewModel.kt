package com.example.cocktails.viewmodel

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktails.model.entities.NonAlcoholicDrink
import com.example.cocktails.network.CocktailsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers


import kotlinx.coroutines.launch
import java.io.IOException


class NonAlcoholicDrinksViewModel : ViewModel() {
    private val cocktailsApiService = CocktailsApiService()
    private val compositeDisposable = CompositeDisposable()

    val _loadNonAlcoholicDrinks = MutableLiveData<Boolean>()
//        val loadNonAlcoholicDrinks: LiveData<Boolean>
//                get() = _loadNonAlcoholicDrinks

    val _nonAlcoholicDrinksResponse = MutableLiveData<NonAlcoholicDrink.Drinks?>()
//        val nonAlcoholicDrinksResponse: LiveData<NonAlcoholicDrink.Drinks?>
//            get() = _nonAlcoholicDrinksResponse

    val _nonAlcoholicDrinksError = MutableLiveData<Boolean>()
//    val nonAlcoholicDrinksError: LiveData<Boolean>
//        get() = _nonAlcoholicDrinksError

    fun getNonAlcoholicDrinks(){
        _loadNonAlcoholicDrinks.value = true

        compositeDisposable.add(cocktailsApiService.getNonAlcoholicDrinks()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<NonAlcoholicDrink.Drinks>(){
                override fun onSuccess(value: NonAlcoholicDrink.Drinks?) {
                    _loadNonAlcoholicDrinks.value = false
                    _nonAlcoholicDrinksResponse.value = value
                    _nonAlcoholicDrinksError.value = false

                }

                override fun onError(e: Throwable) {
                    _loadNonAlcoholicDrinks.value = false
                    _nonAlcoholicDrinksError.value = true
                    e!!.printStackTrace()
                }

            })

        )
    }

}