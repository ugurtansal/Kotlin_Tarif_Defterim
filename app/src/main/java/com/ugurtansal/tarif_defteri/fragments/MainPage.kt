package com.ugurtansal.tarif_defteri.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ugurtansal.tarif_defteri.data.Category
import com.ugurtansal.tarif_defteri.adapter.CategoryAdapter
import com.ugurtansal.tarif_defteri.databinding.FragmentMainPageBinding
import com.ugurtansal.tarif_defteri.viewmodel.MainPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainPage : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainPageViewModel by viewModels()
        viewModel= tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMainPageBinding.inflate(inflater, container, false)

        viewModel.categoryList.observe(viewLifecycleOwner) {
            val categoryAdapter= CategoryAdapter(requireContext(), it, viewModel);
            binding.categoriesRv.adapter=categoryAdapter;
        }


        binding.categoriesRv.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);


        binding.newCategoryCV.setOnClickListener {
            binding.categoryNameTv.requestFocus()
            binding.checkIv.visibility= View.VISIBLE;
            binding.checkIv.isClickable=true;
            binding.addIv.visibility= View.INVISIBLE;
        }

        binding.rootLayout.setOnClickListener {
            binding.categoryNameTv.clearFocus()
            binding.checkIv.visibility= View.INVISIBLE;
            binding.checkIv.isClickable=false;
            binding.addIv.visibility= View.VISIBLE;

            Handler(Looper.getMainLooper()).postDelayed({
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.categoryNameTv.windowToken, 0)
            }, 100)
        }

        binding.categoriesRv.setOnTouchListener { _, _ ->
            binding.categoryNameTv.clearFocus()
            binding.checkIv.visibility = View.INVISIBLE
            binding.checkIv.isClickable = false
            binding.addIv.visibility = View.VISIBLE

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.categoryNameTv.windowToken, 0)

            false
        }

        binding.checkIv.setOnClickListener {
            val categoryName=binding.categoryNameTv.text.toString().trim();
            if (categoryName.isNotEmpty() && viewModel.categoryList.value?.any { it.name == categoryName } != true) {
                viewModel.addCategory(categoryName);
                binding.categoryNameTv.setText("");
                binding.checkIv.visibility= View.INVISIBLE;
                binding.checkIv.isClickable=false;
                binding.addIv.visibility= View.VISIBLE;
                binding.categoryNameTv.clearFocus()

                // Klavyeyi kapat
                Handler(Looper.getMainLooper()).postDelayed({
                    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.categoryNameTv.windowToken, 0)
                }, 100) // 100 ms yeterli olur genelde
            }
            else{
                MaterialAlertDialogBuilder(this.requireContext()).setTitle("Hata")
                    .setMessage("Boş veya aynı isimde bir kategori eklenemez").setPositiveButton("Tamam") { dialog, which ->
                        dialog.dismiss()
                    }.show()
            }
        }







        return binding.root
    }


}