package com.example.cocktails.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cocktails.R
import com.example.cocktails.databinding.ActivityExploreCocktailsBinding

class ExploreCocktailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreCocktailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_explore_cocktails)
    }
}