package com.example.cocktails.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentExploreCocktailsBinding

import com.example.cocktails.viewmodel.ExploreCocktailsViewModel

class ExploreCocktailsFragment : Fragment() {

    private lateinit var viewModel: ExploreCocktailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentExploreCocktailsBinding>(inflater, R.layout.fragment_explore_cocktails, container, false)
        viewModel = ViewModelProvider(this)[ExploreCocktailsViewModel::class.java]

        binding.viewModel = viewModel
        viewModel.navigateToNonAlcoholicDrinks.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateToNonAlcoholicDrinks()
            }
        })

        viewModel.navigateToRandomCocktail.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateToRandomCocktail()
            }
        })

        return binding.root
    }

    fun navigateToNonAlcoholicDrinks() {
        findNavController().navigate(ExploreCocktailsFragmentDirections.actionExploreCocktailsToNonAlcoholicDrinksFragment())
        viewModel.btnNavigateToNonAlcoholicDrinksFinished()
    }

    fun navigateToRandomCocktail() {
        findNavController().navigate(ExploreCocktailsFragmentDirections.actionExploreCocktailsToRandomCocktailFragment())
        viewModel.btnNavigateToRandomCocktailFinished()
    }
}