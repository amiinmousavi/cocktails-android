package com.example.cocktails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktails.model.entities.RandomCocktail
import com.example.cocktails.network.CocktailsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RandomCocktailViewModel : ViewModel(){
    private val cocktailsApiService = CocktailsApiService()
    private val compositeDisposable = CompositeDisposable()

    private val _loadRandomCocktail = MutableLiveData<Boolean>()
        val loadRandomCocktail: LiveData<Boolean>
            get() = _loadRandomCocktail

    private val _randomCocktailResponse = MutableLiveData<RandomCocktail.Drinks?>()
        val randomCocktailResponse: LiveData<RandomCocktail.Drinks?>
            get() = _randomCocktailResponse

    private val _randomCocktailError = MutableLiveData<Boolean>()
        val randomCocktailError: LiveData<Boolean>
            get() = _randomCocktailError

    fun getRandomCocktails(){
        _loadRandomCocktail.value = true

        compositeDisposable.add(cocktailsApiService.getRandomCocktail()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<RandomCocktail.Drinks>(){
                override fun onSuccess(value: RandomCocktail.Drinks?) {
                    _loadRandomCocktail.value = false
                    _randomCocktailResponse.value = value
                    _randomCocktailError.value = false

                }

                override fun onError(e: Throwable) {
                    _loadRandomCocktail.value = false
                    _randomCocktailError.value = true
                    e!!.printStackTrace()
                }

            })

        )
    }
}