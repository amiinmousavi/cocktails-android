package com.example.cocktails.network

import com.example.cocktails.model.entities.NonAlcoholicDrink
import com.example.cocktails.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CocktailsApiService {
    private val api = Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(CocktailsApi::class.java)

    fun getNonAlcoholicDrinks(): Single<NonAlcoholicDrink.Drinks> {
        return api.getNonAlcoholicDrinks()
    }
}