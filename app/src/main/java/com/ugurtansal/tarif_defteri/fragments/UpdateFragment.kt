package com.ugurtansal.tarif_defteri.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.databinding.FragmentUpdateBinding
import com.ugurtansal.tarif_defteri.viewmodel.FoodNamesViewModel
import com.ugurtansal.tarif_defteri.viewmodel.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateFragment : Fragment() {
   private lateinit var binding: FragmentUpdateBinding
   private lateinit var viewModel: UpdateViewModel
   private val sharedViewModel: FoodNamesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: UpdateViewModel by viewModels()
        viewModel= tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle: UpdateFragmentArgs by navArgs()
        val food=bundle.foodObject;
        val categoryId=bundle.categoryId;
        val foodList=sharedViewModel.foodList.value;

        food?.let {
            binding.foodNamePt.setText(it.name);
            binding.ingredientsTv.setText(it.ingredients);
            binding.recipeTv.setText(it.description);
        }

        binding.constReturnBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.updateBtn.setOnClickListener {
            val foodName = binding.foodNamePt.text.toString().trim()
            val ingredients = binding.ingredientsTv.text.toString().trim()
            val recipe = binding.recipeTv.text.toString().trim()

            viewModel.isDuplicateName(foodName, food?.id?:0).observe(viewLifecycleOwner) { isDuplicate ->

            if (foodName.isNotEmpty() && ingredients.isNotEmpty()) {
                if (isDuplicate) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Hata")
                        .setMessage("Aynı kategoride aynı isimde yemek eklenemez.")
                        .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
                        .show()
                } else {
                    if (food != null) {
                        food.name = foodName
                        food.ingredients = ingredients
                        food.description = recipe
                        viewModel.updateFood(food)
                    } else {
                        val newFood = Food(
                            id = 0,
                            name = foodName,
                            ingredients = ingredients,
                            description = recipe,
                            categoryId = categoryId
                        )
                        viewModel.addFood(newFood)
                    }
                    findNavController().navigateUp()
                }
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Hata")
                    .setMessage("Boş alan bırakılamaz.")
                    .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
                }
        }


        return binding.root
    }


}