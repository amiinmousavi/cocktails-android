package com.example.cocktails.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cocktails.databinding.FragmentFavoriteCocktailsBinding
import com.example.cocktails.viewmodel.FavoriteCocktailsViewModel

class FavoriteCocktailsFragment : Fragment() {
    private var _binding: FragmentFavoriteCocktailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteCocktailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(FavoriteCocktailsViewModel::class.java)

        _binding = FragmentFavoriteCocktailsBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}