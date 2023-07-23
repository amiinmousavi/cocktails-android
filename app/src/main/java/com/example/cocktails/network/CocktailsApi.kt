package com.example.cocktails.network

import com.example.cocktails.model.entities.NonAlcoholicDrink
import com.example.cocktails.utils.Constants
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CocktailsApi {
    @GET(Constants.API_ENDPOINT_NON_ALCOHOLIC)
    fun getNonAlcoholicDrinks(): Single<NonAlcoholicDrink.Drinks>
}