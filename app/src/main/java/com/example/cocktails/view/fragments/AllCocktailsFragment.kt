package com.example.cocktails.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.cocktails.R
import com.example.cocktails.application.CocktailApplication
import com.example.cocktails.databinding.FragmentAllCocktailsBinding
import com.example.cocktails.view.activities.AddUpdateCocktailActivity
import com.example.cocktails.viewmodel.CocktailViewModel
import com.example.cocktails.viewmodel.CocktailViewModelFactory
import com.example.cocktails.viewmodel.HomeViewModel

class AllCocktailsFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val cocktailViewModel: CocktailViewModel by viewModels {
        CocktailViewModelFactory(
            (requireActivity().application as
                    CocktailApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private var _binding: FragmentAllCocktailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentAllCocktailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvNoCocktailsAddedYet

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.i("Cocktail", "onViewCreated works")
        super.onViewCreated(view, savedInstanceState)
        cocktailViewModel.allCocktails.observe(viewLifecycleOwner) { cocktails ->
            cocktails.let {
                for (item in it) {
                    Log.i("Cocktail", "${item.idDrink} :: ${item.strDrink}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_cocktails, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_cocktail -> {
                startActivity(Intent(requireActivity(), AddUpdateCocktailActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}