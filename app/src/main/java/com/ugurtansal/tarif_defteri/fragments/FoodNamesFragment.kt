package com.ugurtansal.tarif_defteri.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurtansal.tarif_defteri.data.Food
import com.ugurtansal.tarif_defteri.adapter.FoodAdapter
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.databinding.FragmentFoodNamesBinding
import com.ugurtansal.tarif_defteri.viewmodel.FoodNamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodNamesFragment : Fragment() {
    private lateinit var binding: FragmentFoodNamesBinding
    private lateinit var viewModel: FoodNamesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodNamesViewModel by viewModels()
        viewModel = tempViewModel



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding= FragmentFoodNamesBinding.inflate(inflater, container, false);

        val bundle: FoodNamesFragmentArgs by navArgs()
        val category= bundle.category;
        viewModel.setCategoryId(category.id)


//        viewModel.foodList.observe(viewLifecycleOwner) {
//        //Dinleme
//        val foodAdapter = FoodAdapter(requireContext(), it, category, viewModel)
//        binding.foodNamesRv.adapter = foodAdapter;
//        }

        // STATEFLOW GÖZLEMLEME
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.foodList.collect { foodList ->
                    val foodAdapter = FoodAdapter(requireContext(), foodList, category, viewModel)
                    binding.foodNamesRv.adapter = foodAdapter
                }
            }
        }


        binding.headerTV.text=category.name;

        binding.newFoodCV.setOnClickListener {
            val pass = FoodNamesFragmentDirections.foodNamesToUpdate(
                foodObject = null,
                categoryId = category.id,
                foodList = (viewModel.foodList.value ?: emptyList()).toTypedArray()
            )
            Navigation.findNavController(it).navigate(pass)
        }



        binding.foodNamesRv.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);



        return binding.root;
    }


}