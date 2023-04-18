package com.example.cocktails.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.cocktails.R
import com.example.cocktails.databinding.ActivityAddUpdateCocktailBinding

class AddUpdateCocktailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBinding: ActivityAddUpdateCocktailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddUpdateCocktailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupActionBar()

        mBinding.ivAddCocktailImage.setOnClickListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(mBinding.toolbarAddCocktailActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbarAddCocktailActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        if(v!= null) {
            when (v.id) {
                R.id.iv_add_cocktail_image -> {
                    Toast.makeText(this, "You have clicked the ImageView",
                        Toast.LENGTH_SHORT).show()
                    return
                }
            }
        }
    }

}