package com.example.cocktails.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentFavoriteCocktailsBinding
import com.example.cocktails.viewmodel.FavoriteCocktailsViewModel

class FavoriteCocktailsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteCocktailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentFavoriteCocktailsBinding>(
            inflater, R.layout.fragment_favorite_cocktails, container, false
        )
        return binding.root

    }

}