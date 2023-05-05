package com.example.cocktails.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentNonAlcoholicDrinksBinding
import com.example.cocktails.viewmodel.NonAlcoholicDrinksViewModel

class NonAlcoholicDrinksFragment : Fragment() {
    private var _binding: FragmentNonAlcoholicDrinksBinding? = null
    val binding get() = _binding!!

    private lateinit var viewModel: NonAlcoholicDrinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =DataBindingUtil.inflate(inflater, R.layout.fragment_non_alcoholic_drinks, container, false)
        return binding.root
    }
}