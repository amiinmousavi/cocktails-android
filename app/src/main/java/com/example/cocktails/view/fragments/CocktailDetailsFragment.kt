package com.example.cocktails.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cocktails.databinding.FragmentCocktailDetailsBinding
import java.io.IOException

class CocktailDetailsFragment : Fragment() {

    private var binding : FragmentCocktailDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
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

            binding!!.tvTitle.text = it.cocktailData.strDrink
            binding!!.tvAlcoholic.text = it.cocktailData.strAlcoholic
            binding!!.tvCategory.text = it.cocktailData.strCategory
            binding!!.tvGlass.text = it.cocktailData.strGlass
            binding!!.tvInstruction.text = it.cocktailData.strInstructions
            binding!!.tvIngredient.text = it.cocktailData.strIngredient
            binding!!.tvMeasure.text = it.cocktailData.strMeasure
            binding!!.tvDateModified.text = it.cocktailData.dateModified
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}