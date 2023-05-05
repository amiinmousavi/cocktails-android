package com.example.cocktails.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentExploreCocktailsBinding
import com.example.cocktails.viewmodel.ExploreCocktailsViewModel

class ExploreCocktailsFragment : Fragment() {
    private var _binding: FragmentExploreCocktailsBinding? = null
    val binding get() = _binding!!

    private lateinit var viewModel: ExploreCocktailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore_cocktails, container, false)
        return binding.root
    }

}