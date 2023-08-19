package com.example.cocktails.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.cocktails.R
import com.example.cocktails.application.CocktailApplication
import com.example.cocktails.databinding.FragmentRandomCocktailBinding
import com.example.cocktails.model.entities.Cocktail
import com.example.cocktails.model.entities.RandomCocktail
import com.example.cocktails.utils.Constants
import com.example.cocktails.viewmodel.CocktailViewModel
import com.example.cocktails.viewmodel.CocktailViewModelFactory
import com.example.cocktails.viewmodel.NonAlcoholicDrinksViewModel
import com.example.cocktails.viewmodel.RandomCocktailViewModel

class RandomCocktailFragment : Fragment() {
    private var _binding: FragmentRandomCocktailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RandomCocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomCocktailBinding.inflate(inflater,container, false);
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RandomCocktailViewModel::class.java)

        viewModel.getRandomCocktails()
        randomCocktailViewModelObserver()
    }

    private fun setRandomCocktailResponseInUI(cocktailData: RandomCocktail.Drink){
        Glide.with(requireActivity())
            .load(cocktailData.strDrinkThumb)
            .centerCrop()
            .into(_binding!!.ivCocktailImage)

        _binding!!.tvTitle.text = cocktailData.strDrink
        _binding!!.tvAlcoholic.text = cocktailData.strAlcoholic
        _binding!!.tvCategory.text = cocktailData.strCategory
        _binding!!.tvGlass.text = cocktailData.strGlass
        _binding!!.tvInstruction.text = cocktailData.strInstructions
        _binding!!.tvIngredient.text = "yet to implement"
        _binding!!.tvMeasure.text = "yet to implement"
        _binding!!.tvDateModified.text = cocktailData.dateModified

        _binding!!.ivFavoriteCocktail.setOnClickListener{
            val cocktailDetails = Cocktail(
                cocktailData.strDrinkThumb,
                Constants.COCKTAIL_IMAGE_SOURCE_ONLINE,
                cocktailData.strDrink,
                cocktailData.strAlcoholic,
                cocktailData.strGlass,
                cocktailData.strInstructions,
                "cocktailData.strIngedrient",
                "cocktail",
                "strMeasure",
                cocktailData.dateModified,
                true
            )
            val cocktailViewModel: CocktailViewModel by viewModels {
                CocktailViewModelFactory((requireActivity().application as CocktailApplication).repository)
            }
            cocktailViewModel.insert(cocktailDetails)
        }
    }

    private fun randomCocktailViewModelObserver() {
        viewModel.randomCocktailResponse.observe(viewLifecycleOwner,
            { randomCocktailResponse ->
                randomCocktailResponse?.let {
                    Log.i("RC", "${randomCocktailResponse.drinks[0]}")
                    setRandomCocktailResponseInUI(randomCocktailResponse.drinks[0])
                }})

        viewModel.randomCocktailError.observe(viewLifecycleOwner,
            { dataError ->
                dataError?.let {
                    Log.e("RC Error", "$dataError")
                }
            })
        viewModel.loadRandomCocktail.observe(viewLifecycleOwner, {
                loadRandomCocktails ->
            loadRandomCocktails?.let{
                Log.i("RC Loading", "$loadRandomCocktails")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}