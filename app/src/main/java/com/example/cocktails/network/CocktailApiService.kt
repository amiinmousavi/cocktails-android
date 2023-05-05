package com.example.cocktails.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class CocktailApiService {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())

//    interface CocktailApiService {
//        fun
//    }
}