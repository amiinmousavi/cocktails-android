package com.example.cocktails.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktails.R
import com.example.cocktails.application.CocktailApplication
import com.example.cocktails.databinding.FragmentAllCocktailsBinding
import com.example.cocktails.model.entities.Cocktail
import com.example.cocktails.view.activities.AddUpdateCocktailActivity
import com.example.cocktails.view.activities.MainActivity
import com.example.cocktails.view.adapters.CocktailListAdapter
import com.example.cocktails.viewmodel.AllCocktailsViewModel
import com.example.cocktails.viewmodel.AllCocktailsViewModelFactory

// activities, fragments: responsible for drawing views and data to the screen
class AllCocktailsFragment : Fragment() {
    // property delegate (by viewModels) retains value through conf changes
    private val viewModel: AllCocktailsViewModel by viewModels {
        AllCocktailsViewModelFactory(
            (requireActivity().application as
                    CocktailApplication).repository
        )
    }
    private lateinit var binding: FragmentAllCocktailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentAllCocktailsBinding>(
            inflater, R.layout.fragment_all_cocktails, container, false
        )
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCocktailsList.layoutManager = GridLayoutManager(requireActivity(), 2)
        val cocktailListAdapter = CocktailListAdapter(this@AllCocktailsFragment)
        binding.rvCocktailsList.adapter = cocktailListAdapter

        viewModel.allCocktails.observe(viewLifecycleOwner) { cocktails ->
            cocktails.let {
                for (item in it) {
                    if(it.isNotEmpty()){
                        cocktailListAdapter.cocktailsList(it)
                    }
                }
            }
        }
    }

    fun cocktailDetails(cocktail: Cocktail) {
        findNavController().navigate(AllCocktailsFragmentDirections.actionAllCocktailsToCocktailDetails(
            cocktail
        ))

        if(requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }
    }

    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavigationView()
        }
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