package com.example.cocktails.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cocktails.R
import com.example.cocktails.databinding.FragmentAllCategoriesBinding
import com.example.cocktails.viewmodel.AllCategoriesViewModel

class AllCategoriesFragment : Fragment() {
    private var _binding: FragmentAllCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AllCategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_categories, container, false)
        return binding.root
    }

}