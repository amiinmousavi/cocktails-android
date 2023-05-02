package com.example.cocktails.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktails.databinding.ItemCocktailListBinding
import com.example.cocktails.model.entities.Cocktail
import com.example.cocktails.view.fragments.AllCocktailsFragment

class CocktailListAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    class ViewHolder(view: ItemCocktailListBinding) : RecyclerView.ViewHolder(view.root) {
        val ivCocktailImage = view.ivCocktailImage
        val tvTitle = view.tvCocktailTitle
    }

    private var cocktails: List<Cocktail> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCocktailListBinding = ItemCocktailListBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktails[position]
        Glide.with(fragment)
            .load(cocktail.strDrinkThumb)
            .into(holder.ivCocktailImage)
        holder.tvTitle.text = cocktail.strDrink

        holder.itemView.setOnClickListener{
            if(fragment is AllCocktailsFragment) {
                fragment.cocktailDetails(cocktail)
            }
        }
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    fun cocktailsList(list: List<Cocktail>) {
        cocktails = list
        notifyDataSetChanged()
    }

}