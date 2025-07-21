package com.ugurtansal.tarif_defteri.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ugurtansal.tarif_defteri.databinding.FragmentDetailBinding
import com.ugurtansal.tarif_defteri.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(inflater, container, false)
        val bundle: DetailFragmentArgs by navArgs()
        val food=bundle.foodObject;

        binding.nameTv.text=food.name;
        binding.ingredientsTv.text=food.ingredients;
        binding.recipeTv.text=food.description;

        binding.editIcon.setOnClickListener {
            val pass= DetailFragmentDirections.detailToUpdate(foodObject = food, categoryId = food.categoryId, foodList = null)
            Navigation.findNavController(it).navigate(pass)
        }
        binding.constReturnBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding

        return binding.root;
    }


}