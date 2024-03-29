package com.example.cocktails.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktails.databinding.ItemCustomListBinding
import com.example.cocktails.view.activities.AddUpdateCocktailActivity

class CustomListItemAdapter(private val activity: Activity,
                            private val listItems : List<String>,
                            private val selection: String)
    : RecyclerView.Adapter<CustomListItemAdapter.ViewHolder>(){
        class ViewHolder(view: ItemCustomListBinding): RecyclerView.ViewHolder(view.root){
            val tvText = view.tvText
        }

    // called once viewholder is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCustomListBinding = ItemCustomListBinding
            .inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    // called for every item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvText.text = item
        holder.itemView.setOnClickListener{
            if(activity is AddUpdateCocktailActivity){
                activity.selectedListItem(item, selection)
            }
        }
    }

    // returns how many items there are
    override fun getItemCount(): Int {
        return listItems.size
    }


}