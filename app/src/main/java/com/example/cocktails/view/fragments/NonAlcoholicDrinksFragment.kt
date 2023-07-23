package com.example.cocktails.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentNonAlcoholicDrinksBinding
import com.example.cocktails.viewmodel.NonAlcoholicDrinksViewModel

class NonAlcoholicDrinksFragment : Fragment() {
    private var binding: FragmentNonAlcoholicDrinksBinding? = null
    private lateinit var viewModel: NonAlcoholicDrinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNonAlcoholicDrinksBinding.inflate(inflater,container, false);
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NonAlcoholicDrinksViewModel::class.java)

        viewModel.getNonAlcoholicDrinks()
        nonAlcoholicDrinksViewModelObserver()
    }
    private fun nonAlcoholicDrinksViewModelObserver() {
        viewModel._nonAlcoholicDrinksResponse.observe(viewLifecycleOwner,
         { nonAlcoholicDrinksResponse ->
            nonAlcoholicDrinksResponse?.let {
                Log.i("NAD", "${nonAlcoholicDrinksResponse.drinks}")
            }})

        viewModel._nonAlcoholicDrinksError.observe(viewLifecycleOwner,
         { dataError ->
            dataError?.let {
                Log.e("NAD Error", "$dataError")
            }
        })
        viewModel._loadNonAlcoholicDrinks.observe(viewLifecycleOwner, {
            loadNonAlcoholicDrinks ->
            loadNonAlcoholicDrinks?.let{
                Log.i("NAD Loading", "$loadNonAlcoholicDrinks")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}