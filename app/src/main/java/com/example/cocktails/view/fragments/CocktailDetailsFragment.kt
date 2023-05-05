package com.example.cocktails.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentAllCategoriesBinding
import com.example.cocktails.databinding.FragmentCocktailDetailsBinding
import com.example.cocktails.viewmodel.AllCategoriesViewModel
import com.example.cocktails.viewmodel.AllCocktailsViewModel
import java.io.IOException

class CocktailDetailsFragment : Fragment() {
    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AllCocktailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cocktail_details, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CocktailDetailsFragmentArgs by navArgs()

        args.let{
            try {
                Glide.with(requireActivity())
                    .load(it.cocktailData.strDrinkThumb)
                    .centerCrop()
                    .into(binding!!.ivCocktailImage)
            } catch(e: IOException) {
                e.printStackTrace()
            }
            _binding!!.tvTitle.text = it.cocktailData.strDrink
            _binding!!.tvAlcoholic.text = it.cocktailData.strAlcoholic
            _binding!!.tvCategory.text = it.cocktailData.strCategory
            _binding!!.tvGlass.text = it.cocktailData.strGlass
            _binding!!.tvInstruction.text = it.cocktailData.strInstructions
            _binding!!.tvIngredient.text = it.cocktailData.strIngredient
            _binding!!.tvMeasure.text = it.cocktailData.strMeasure
            _binding!!.tvDateModified.text = it.cocktailData.dateModified
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}